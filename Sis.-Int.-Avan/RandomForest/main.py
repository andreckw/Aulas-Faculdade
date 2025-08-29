import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from sklearn import metrics
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay
from imblearn.over_sampling import SMOTE
import os
from pickle import dump, load
from sklearn.model_selection import RandomizedSearchCV, cross_validate
from sklearn.ensemble import RandomForestClassifier

pd.set_option('display.max_rows', None)

class RandomForest():
    
    def treinar(self):
        
        df = pd.read_excel("app_data.xlsx", 0)
        print("Iniciando pre processamento...")
        df = self._pre_processar(df)
        print("Finalizado pre processamento...")
        print("Iniciando normalizacao...")
        df = self._normalizar(df)
        print("Finalizado normalizacao...")
        print("Iniciando Balanceamento...")
        dfs = self._balancear(df)
        print("Finalizado Balanceamento...")
        print("Iniciando Hyper Parametrizacao...")
        hyper_parametros = self._hyper_parametizar(dfs)
        print("Finalizado Hyper Parametrizacao...")
        
        for k, v in dfs.items():
            dados = v.drop(v.columns[-1], axis=1)
            classes = v.drop(columns=dados.columns)
            
            forest = RandomForestClassifier(**hyper_parametros[k])
            
            forest_model = forest.fit(dados, classes)
            
            if not os.path.isdir("modelos"):
                os.mkdir("modelos")

            class_predict = forest_model.predict(dados)
            
            scoring = ["precision_macro", "recall_macro"]

            score_cross = cross_validate(forest, dados, classes, cv=10, scoring=scoring)

            print("matriz da sensibilidade: ", score_cross['test_precision_macro'])
            print("matriz da especifidade: ", score_cross['test_recall_macro'])
            print("sensibilidade: ", score_cross['test_precision_macro'].mean())
            print("especifidade: ", score_cross['test_recall_macro'].mean())
            
            dump(forest_model, open(f"modelos/{k}.model", "wb"))
    
    def _pre_processar(self, df: pd.DataFrame):
        """Pre processa o dataframe

        Args:
            df (pd.DataFrame): dataframe

        Returns:
            pd.DataFrame: o DataFrame pre processado
        """
        # Tirando colunas não utilizadas ou problematicas
        df = df.drop(columns=["Diagnosis_Presumptive", "US_Number", "Gynecological_Findings",
                         "Enteritis", "Meteorism", "Coprostasis", "Ileus", 
                         "Conglomerate_of_Bowel_Loops", "Bowel_Wall_Thickening", 
                         "Lymph_Nodes_Location", "Pathological_Lymph_Nodes", "Abscess_Location",
                         "Appendicular_Abscess", "Surrounding_Tissue_Reaction", 
                         "Perforation", "Perfusion", "Appendicolith", "Target_Sign", "Appendix_Wall_Layers",
                         "Segmented_Neutrophils"])
        
        # Fazendo a media e moda deles
        df["Age"] = df["Age"].fillna(df["Age"].mean())
        df["BMI"] = df["BMI"].fillna(df["BMI"].mean())
        df["Height"] = df["Height"].fillna(df["Height"].mean())
        df["Weight"] = df["Weight"].fillna(df["Weight"].mean())
        df["Length_of_Stay"] = df["Length_of_Stay"].fillna(df["Length_of_Stay"].mean())
        df["Alvarado_Score"] = df["Alvarado_Score"].fillna(df["Alvarado_Score"].mean())
        df["Body_Temperature"] = df["Body_Temperature"].fillna(df["Body_Temperature"].mean())
        df["Paedriatic_Appendicitis_Score"] = df["Paedriatic_Appendicitis_Score"].fillna(df["Paedriatic_Appendicitis_Score"].mean())
        df["Appendix_Diameter"] = df["Appendix_Diameter"].fillna(df["Appendix_Diameter"].mean())
        df["WBC_Count"] = df["WBC_Count"].fillna(df["WBC_Count"].mean())
        df["RBC_Count"] = df["RBC_Count"].fillna(df["RBC_Count"].mean())
        df["Neutrophil_Percentage"] = df["Neutrophil_Percentage"].fillna(df["Neutrophil_Percentage"].mean())
        df["Hemoglobin"] = df["Hemoglobin"].fillna(df["Hemoglobin"].mean())
        df["RDW"] = df["RDW"].fillna(df["RDW"].mean())
        df["Thrombocyte_Count"] = df["Thrombocyte_Count"].fillna(df["Thrombocyte_Count"].mean())
        df["CRP"] = df["CRP"].fillna(df["CRP"].mean())
        
        df["Sex"] = df["Sex"].fillna(df["Sex"].mode()[0])
        df["Management"] = df["Management"].fillna(df["Management"].mode()[0])
        df["Severity"] = df["Severity"].fillna(df["Severity"].mode()[0])
        df["Diagnosis"] = df["Diagnosis"].fillna(df["Diagnosis"].mode()[0])
        df["Appendix_on_US"] = df["Appendix_on_US"].fillna(df["Appendix_on_US"].mode()[0])
        df["Migratory_Pain"] = df["Migratory_Pain"].fillna(df["Migratory_Pain"].mode()[0])
        df["Lower_Right_Abd_Pain"] = df["Lower_Right_Abd_Pain"].fillna(df["Lower_Right_Abd_Pain"].mode()[0])
        df["Contralateral_Rebound_Tenderness"] = df["Contralateral_Rebound_Tenderness"].fillna(df["Contralateral_Rebound_Tenderness"].mode()[0])
        df["Coughing_Pain"] = df["Coughing_Pain"].fillna(df["Coughing_Pain"].mode()[0])
        df["Nausea"] = df["Nausea"].fillna(df["Nausea"].mode()[0])
        df["Loss_of_Appetite"] = df["Loss_of_Appetite"].fillna(df["Loss_of_Appetite"].mode()[0])
        df["Neutrophilia"] = df["Neutrophilia"].fillna(df["Neutrophilia"].mode()[0])
        df["Ketones_in_Urine"] = df["Ketones_in_Urine"].fillna(df["Ketones_in_Urine"].mode()[0])
        df["RBC_in_Urine"] = df["RBC_in_Urine"].fillna(df["RBC_in_Urine"].mode()[0])
        df["WBC_in_Urine"] = df["WBC_in_Urine"].fillna(df["WBC_in_Urine"].mode()[0])
        df["Dysuria"] = df["Dysuria"].fillna(df["Dysuria"].mode()[0])
        df["Stool"] = df["Stool"].fillna(df["Stool"].mode()[0])
        df["Peritonitis"] = df["Peritonitis"].fillna(df["Peritonitis"].mode()[0])
        df["Psoas_Sign"] = df["Psoas_Sign"].fillna(df["Psoas_Sign"].mode()[0])
        df["Ipsilateral_Rebound_Tenderness"] = df["Ipsilateral_Rebound_Tenderness"].fillna(df["Ipsilateral_Rebound_Tenderness"].mode()[0])
        df["US_Performed"] = df["US_Performed"].fillna(df["US_Performed"].mode()[0])
        df["Free_Fluids"] = df["Free_Fluids"].fillna(df["Free_Fluids"].mode()[0])
        
        return df
        
    
    def _normalizar(self, df: pd.DataFrame):
        
        df_numerico = df.drop(columns=[
            "Sex", "Management", "Severity", "Diagnosis", "Appendix_on_US", "Migratory_Pain", "Lower_Right_Abd_Pain",
            "Contralateral_Rebound_Tenderness", "Coughing_Pain", "Nausea", "Loss_of_Appetite", "Neutrophilia", "Ketones_in_Urine",
            "RBC_in_Urine", "WBC_in_Urine", "Dysuria", "Stool", "Peritonitis", "Psoas_Sign", "Ipsilateral_Rebound_Tenderness", "US_Performed", "Free_Fluids"
            
        ])
        
        df_categorico = df.drop(columns=df_numerico.columns)
        df_categorico = df_categorico.drop(columns=["Management", "Severity", "Diagnosis"])
        
        df_classes = df.drop(columns=df_numerico)
        df_classes = df_classes.drop(columns=df_categorico)
        
        normalizador = MinMaxScaler()
        modelo_normalizador = normalizador.fit(df_numerico)
        
        if not os.path.isdir("modelos"):
            os.mkdir("modelos")
        
        dump(modelo_normalizador, open("modelos/normalizador_app_data.model", "wb"))
        
        
        df_categorico = pd.get_dummies(df_categorico, dtype=int)
        df = pd.DataFrame(modelo_normalizador.fit_transform(df_numerico), columns=df_numerico.columns)
        
        return df.join([df_categorico, df_classes])

    
    def _balancear(self, df: pd.DataFrame):
        """Balanceamento do dataframe

        Args:
            df (pd.DataFrame): Dataframe

        Returns:
            dictonary: {
                'df_management': pd.DataFrame
                'df_severity': pd.DataFrame
                'df_diagnosis': pd.DataFrame
            }
        """
        resampler = SMOTE()
        
        df_dados = df.drop(columns=["Severity", "Diagnosis", "Management"])
        df_classes = df.drop(columns=df_dados.columns)
        
        # print(df_classes["Management"].value_counts())
        
        dados_management, classes_management = resampler.fit_resample(df_dados, df_classes["Management"])
        dados_severity, classes_severity = resampler.fit_resample(df_dados, df_classes["Severity"])
        dados_diagnosis, classes_diagnosis = resampler.fit_resample(df_dados, df_classes["Diagnosis"])
        
        
        return {
            "df_management": pd.DataFrame(dados_management.join(classes_management)),
            "df_severity": pd.DataFrame(dados_severity.join(classes_severity)),
            "df_diagnosis": pd.DataFrame(dados_diagnosis.join(classes_diagnosis)),
        }
    
    def _hyper_parametizar(self, dfs: dict[str, pd.DataFrame]):
        
        retorno = {}
        
        for k, v in dfs.items():
            
            dados = v.drop(v.columns[-1], axis=1)
            classes = v.drop(columns=dados.columns)
            
            modelo = RandomForestClassifier()
            
            parametros = {
                "criterion": ["gini", "entropy", "log_loss"],
                "max_depth": [1, 10, 50],
                "max_features": ["sqrt", "log2", None],
                "random_state": [42]
            }
            
            hyperparametros = RandomizedSearchCV(estimator=modelo, param_distributions=parametros, n_iter=10, cv=5, random_state=42)
            
            hyperparametros.fit(dados, classes)

            retorno[k] = hyperparametros.best_params_
            
        return retorno
    
    
    def inferir(self, nova_inferencia: dict):
        
        dados_drop = ["Diagnosis_Presumptive", "US_Number", "Gynecological_Findings", "Enteritis", "Meteorism", "Coprostasis", "Ileus", "Conglomerate_of_Bowel_Loops", "Bowel_Wall_Thickening","Lymph_Nodes_Location", "Pathological_Lymph_Nodes", "Abscess_Location","Appendicular_Abscess", "Surrounding_Tissue_Reaction","Perforation", "Perfusion", "Appendicolith", "Target_Sign", "Appendix_Wall_Layers","Segmented_Neutrophils", "Management", "Severity", "Diagnosis"]
        
        for d in dados_drop:
            if d in nova_inferencia:
                nova_inferencia.pop(d)
        
        dados_categoricos = {}
        colunas_categoricos = ["Sex", "Appendix_on_US", "Migratory_Pain", "Lower_Right_Abd_Pain","Contralateral_Rebound_Tenderness", "Coughing_Pain", "Nausea", "Loss_of_Appetite", "Neutrophilia", "Ketones_in_Urine","RBC_in_Urine", "WBC_in_Urine", "Dysuria", "Stool", "Peritonitis", "Psoas_Sign","Ipsilateral_Rebound_Tenderness", "US_Performed", "Free_Fluids"]
        
        for d in colunas_categoricos:
            if d in nova_inferencia:
                 dados_categoricos[d] = nova_inferencia[d]
        
        
        dados_numericos = []
        colunas_numericas = ["Age", "BMI", "Height", "Weight", "Length_of_Stay", "Alvarado_Score", "Paedriatic_Appendicitis_Score", "Appendix_Diameter", "Body_Temperature", "WBC_Count", "Neutrophil_Percentage", "RBC_Count", "Hemoglobin", "RDW", "Thrombocyte_Count", "CRP"]
        
        for d in colunas_numericas:
            dados_numericos.append(nova_inferencia[d])
            
        
        modelo_normalizar : MinMaxScaler = load(open("modelos/normalizador_app_data.model", "rb"))
        
        dados_numericos = modelo_normalizar.fit_transform([dados_numericos])
        
        colunas_categoricos = ['Sex_female', 'Sex_male', 'Appendix_on_US_no', 'Appendix_on_US_yes',
       'Migratory_Pain_no', 'Migratory_Pain_yes', 'Lower_Right_Abd_Pain_no',
       'Lower_Right_Abd_Pain_yes', 'Contralateral_Rebound_Tenderness_no',
       'Contralateral_Rebound_Tenderness_yes', 'Coughing_Pain_no',
       'Coughing_Pain_yes', 'Nausea_no', 'Nausea_yes', 'Loss_of_Appetite_no',
       'Loss_of_Appetite_yes', 'Neutrophilia_no', 'Neutrophilia_yes',
       'Ketones_in_Urine_+', 'Ketones_in_Urine_++', 'Ketones_in_Urine_+++',
       'Ketones_in_Urine_no', 'RBC_in_Urine_+', 'RBC_in_Urine_++',
       'RBC_in_Urine_+++', 'RBC_in_Urine_no', 'WBC_in_Urine_+',
       'WBC_in_Urine_++', 'WBC_in_Urine_+++', 'WBC_in_Urine_no', 'Dysuria_no',
       'Dysuria_yes', 'Stool_constipation', 'Stool_constipation, diarrhea',
       'Stool_diarrhea', 'Stool_normal', 'Peritonitis_generalized',
       'Peritonitis_local', 'Peritonitis_no', 'Psoas_Sign_no',
       'Psoas_Sign_yes', 'Ipsilateral_Rebound_Tenderness_no',
       'Ipsilateral_Rebound_Tenderness_yes', 'US_Performed_no',
       'US_Performed_yes', 'Free_Fluids_no', 'Free_Fluids_yes']
        
        dados_nulos = []
        
        for c in colunas_categoricos:
            dados_nulos.append(0)
        
        df_categorico = pd.DataFrame([dados_nulos], columns=colunas_categoricos)
        
        for k, v in dados_categoricos.items():
            df_categorico[k+"_"+v] = 1

        df_inferencias = pd.DataFrame(dados_numericos, columns=colunas_numericas)
        
        df_inferencias = df_inferencias.join(df_categorico)
        
        model_diagnosis: RandomForestClassifier = load(open("modelos/df_diagnosis.model", "rb"))
        
        model_management: RandomForestClassifier = load(open("modelos/df_management.model", "rb"))
        
        model_severity: RandomForestClassifier = load(open("modelos/df_severity.model", "rb"))
        
        predict_diagnosis = model_diagnosis.predict_proba(df_inferencias)[0]
        print(f"Tem appendicitis: {predict_diagnosis[0]}%")
        print(f"Não tem appendicitis: {predict_diagnosis[1]}%")
        print("-----------------")
        predict_management = model_management.predict_proba(df_inferencias)[0]
        print(f"Conservative: {predict_management[0]}%")
        print(f"primary surgical: {predict_management[1]}%")
        print(f"secondary surgical: {predict_management[2]}%")
        print("-----------------")
        predict_severity = model_severity.predict_proba(df_inferencias)[0]
        print(f"complicated: {predict_severity[0]}%")
        print(f"uncomplicated: {predict_severity[1]}%")
                
if __name__ == "__main__":
    RandomForest().treinar()
    
    inferencia = {
        "Age":14.34,
        "BMI": 14.9,
        "Sex":"male",
        "Height":174,
        "Weight":45.5,
        "Length_of_Stay":3,
        "Management":"conservative",
        "Severity":"uncomplicated",
        "Diagnosis_Presumptive":"appendicitis",
        "Diagnosis":"appendicitis",
        "Alvarado_Score":4,
        "Paedriatic_Appendicitis_Score":4,
        "Appendix_on_US":"yes",
        "Appendix_Diameter":8,
        "Migratory_Pain":"no",
        "Lower_Right_Abd_Pain":"yes",
        "Contralateral_Rebound_Tenderness":"no",
        "Coughing_Pain":"no",
        "Nausea":"yes",
        "Loss_of_Appetite":"yes",
        "Body_Temperature":37.1,
        "WBC_Count":5.8,
        "Neutrophil_Percentage":47.2,
        "Segmented_Neutrophils":"",
        "Neutrophilia":"no",
        "RBC_Count":4.78,
        "Hemoglobin":12.9,
        "RDW":12.6,
        "Thrombocyte_Count":220,
        "Ketones_in_Urine":"no",
        "RBC_in_Urine":"no",
        "WBC_in_Urine":"no",
        "CRP":0,
        "Dysuria":"no",
        "Stool":"normal",
        "Peritonitis":"no",
        "Psoas_Sign":"no",
        "Ipsilateral_Rebound_Tenderness":"no",
        "US_Performed":"yes",
        "US_Number":893,
        "Free_Fluids":"no",
        "Appendix_Wall_Layers":"intact",
        "Target_Sign":"",
        "Appendicolith":"",
        "Perfusion":"",
        "Perforation":"",
        "Surrounding_Tissue_Reaction":"yes",
        "Appendicular_Abscess":"",
        "Abscess_Location":"",
        "Pathological_Lymph_Nodes":"",
        "Lymph_Nodes_Location":"",
        "Bowel_Wall_Thickening":"",
        "Conglomerate_of_Bowel_Loops":"",
        "Ileus":"",
        "Coprostasis":"",
        "Meteorism":"",
        "Enteritis":"",
        "Gynecological_Findings":"",
    }
    
    RandomForest().inferir(inferencia)