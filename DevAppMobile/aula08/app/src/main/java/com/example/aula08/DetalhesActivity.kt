package com.example.aula08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula08.ui.theme.Aula08Theme

class DetalhesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nome = intent.getStringExtra("nome") ?: "Não informado"
        val email = intent.getStringExtra("email") ?: "Não informado"
        val celular = intent.getStringExtra("celular") ?: "Não informado"

        setContent {
            LayoutDetalhes(onBack = { finish() },
                nome=nome,
                email=email,
                celular=celular
            )
        }
    }
}

@Composable
fun LayoutDetalhes(onBack: () -> Unit, nome: String, email: String, celular: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Detalhes do contato", fontSize = 26.sp, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Nome: $nome", fontSize = 25.sp, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Email: $email", fontSize = 25.sp, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Celular: $celular", fontSize = 25.sp, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = onBack) {
            Text(text = "Voltar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalhesPreview() {
    LayoutDetalhes(onBack = {}, "Porta", "porta@email.com", "9?99?999?9999?")
}