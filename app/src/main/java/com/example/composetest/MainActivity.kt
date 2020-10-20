package com.example.composetest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.Companion.weight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import com.example.composetest.ui.ComposetestTheme

class MainActivity : AppCompatActivity() {

    private val mainVM by lazy {
        ViewModelProvider(this)[MainVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview(mainVM = mainVM)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(mainVM: MainVM = MainVM()) {

    ComposetestTheme(darkTheme = true) {
        Column(

        ) {
            Header()
            MainContent(mainVM = mainVM)

        }
    }
}


@Composable
fun MainContent(mainVM: MainVM) {
    val (content, setContent) = remember {
        mutableStateOf("")
    }
    val items = remember {
        mutableStateListOf<Int>(1,2,4,4,5, -1)
    }
    ScrollableColumn {
        items.forEach {num->

            if (num != -1)
                Text(text = num.toString())
            else
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = content,
                        backgroundColor = Color.Gray,
                        label = {
                        },
                        shape = RoundedCornerShape(
                            10.dp
                        ),
                        keyboardType = KeyboardType.Number,
                        onImeActionPerformed = {action, controller->
                            Log.e("输入法", action.toString())
                        },
                        onValueChange = {value->
                            Log.e("输入文字变化", value)
                            setContent(value)
                        },
                        modifier = Modifier.padding(8.dp),
                    )
                    Button(onClick = {
                        if (content.isNotEmpty()) {
                            items.add(0, content.toInt())
                        }
                    }, modifier = Modifier.padding(8.dp)) {
                        Text(text = "点击添加")
                    }
                }
        }
    }
}

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderLeft()
        HeaderRight()
    }
}

@Composable
fun HeaderLeft() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(16.dp)
            .weight(1f)

    ) {
        Image(
            imageResource(id = R.drawable.test),
            modifier = Modifier
                .background(shape = CircleShape, color = Color.Black)
                .preferredSize(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillHeight
        )
        Column {
            Text(
                text = "title",
                style = TextStyle(
                    fontSize = TextUnit.Companion.Sp(18),
                    color = Color.Black,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(
                    padding = InnerPadding(
                        start = 16.dp
                    )
                )
            )
            Text(
                text = "subTitle",
                style = TextStyle(
                    fontSize = TextUnit.Companion.Sp(15),
                    color = Color.Gray
                ),
                modifier = Modifier.padding(
                    padding = InnerPadding(
                        start = 16.dp,
                        top = 8.dp
                    )
                )
            )
        }
    }
}


@Composable
fun HeaderRight() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            padding = InnerPadding(
                end = 16.dp
            )
        )
    ) {
        Image(
            imageResource(id = R.drawable.test),
            modifier = Modifier.background(
                shape = CircleShape,
                color = Color.Transparent
            ).preferredSize(30.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = "右边的小图",
            style = TextStyle(
                fontSize = TextUnit.Sp(10),
            )
        )
    }
}