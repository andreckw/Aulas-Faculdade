package com.example.aula08

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula08.ui.theme.Aula08Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutMain()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutMain() {
    var nome by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var listaContatos = remember { mutableStateListOf<Contato>() }

    val contexto = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Lista de Contatos", fontSize = 30.sp)
        
        Spacer(modifier = Modifier.height(5.dp))

        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") });
        TextField(value = celular, onValueChange = { celular = it }, label = { Text("Celular") });
        TextField(value = email, onValueChange = { email = it }, label = { Text("E-mail") });

        Button(onClick = {
            val newNome = nome
            val newEmail = email
            val newCelular = celular

            if (newNome.isEmpty() || newEmail.isEmpty() || newCelular.isEmpty()) {
                Toast.makeText(contexto, "Preencha todos os campos necessarios", Toast.LENGTH_SHORT)
                return@Button;
            }

            listaContatos.add(Contato(
                id=System.currentTimeMillis(),
                nome=newNome,
                celular=newEmail,
                email=newCelular)
            )

            nome = ""
            email = ""
            celular = ""
        }) {
            Text(text = "Salvar Contato")
        }

        LazyColumn() {
            items(listaContatos) {

                contatoAtual -> LayoutLista(contato = contatoAtual, contexto = contexto)

            }

        }

    }
}


@Composable
fun LayoutLista(contato: Contato, contexto: Context) {
    Text(text = contato.nome,
        modifier= Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                val intent = Intent(contexto, DetalhesActivity::class.java)

                intent.putExtra("nome", contato.nome)
                intent.putExtra("email", contato.email)
                intent.putExtra("celular", contato.celular)

                contexto.startActivity(intent)
            }
    )
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview() {

    LayoutMain()
}