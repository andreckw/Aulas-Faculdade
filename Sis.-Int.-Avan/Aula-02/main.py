# Normalização de dados - Aula 02
import pandas as pd
from sklearn import preprocessing
from pickle import dump

df = pd.read_csv("/content/drive/MyDrive/Colab/dados_normalizar.csv", sep=";");

# Segumentar dados
dados_num = df.drop(columns=['sexo']) # DF Pandas
dados_cat = df["sexo"] # Serie

# Cria o normalizador
normalizador = preprocessing.MinMaxScaler()

modelo_normalizado_num = normalizador.fit(dados_num)

dump(modelo_normalizado_num, open('/content/drive/MyDrive/Colab/models/normalizador_1.model', 'wb'))

# Transforma os dados numericos entre 0 e 1
dados_num_normalizado = modelo_normalizado_num.fit_transform(dados_num)
dados_num_normalizado.head()

# Transforma os dados categoricos em categorias separadas
dados_cat_normalizado = pd.get_dummies(dados_cat, prefix='Sexo')
dados_cat_normalizado.head()

# Retorna os dados transformados com rotulos
dados_num = pd.DataFrame(dados_num_normalizado, columns=dados_num.columns)
dados_num.head()

# Junta os dados
dados_normalizado = dados_num.join(dados_cat_normalizado)
dados_normalizado.head()