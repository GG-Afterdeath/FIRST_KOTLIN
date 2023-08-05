package com.example.my2687386application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my2687386application.ui.theme.My2687386ApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My2687386ApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    //MessageCard(Message("Android", "Jetpack Compose"))
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(
            painter = painterResource(R.drawable.crackfamily),
            contentDescription = null,
            modifier = Modifier
                .size(55.dp)
                .clip(CutCornerShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CutCornerShape)
        )

        Spacer(modifier = Modifier.width(8.dp))
        // We keep track if the message is expanded or not in this variable
        var isExpanded by remember { mutableStateOf(false) }


        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 3.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.titleMedium
                    // If message is displayed, we display all its content
                    // Otherwise, we only show the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    MessageCard(
        msg = Message("El socio", "Buena, píllese el Jetpack Compose, está una chimba!")
    )
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        // Se le pasa la lista al iterable para que la recorra
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun ConversationPreview() {
    My2687386ApplicationTheme {
        Conversation(SampleData.conversationSample)
    }
}

