import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.millionerv20.ui.theme.Millionerv20Theme

@Composable
fun WinLoseScreen(title: String,
                  modifier: Modifier = Modifier,
                  viewModel: QuestionsViewModel?,
                  nv: NavController?) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    val Typography = Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        )
    )
    Column (modifier = Modifier
        .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = null,
            modifier = Modifier.size(500.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(340.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontSize = 38.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Level 8",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontSize = 19.sp
        )
        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)

            )
            Text(
                text = "$15,000",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                fontSize = 29.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            YellowImageButton()
            BlueImageButton()
            Spacer(modifier = Modifier.height(75.dp))
        }
    }



}
@Composable
fun YellowImageButton() {
    Box(
        modifier = Modifier
            .clickable { println("") }
    ) {

        Image(
            painter = painterResource(id = R.drawable.button_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
fun BlueImageButton() {
    Box(
        modifier = Modifier
            .clickable { println("") },
    ) {
        Image(
            painter = painterResource(id = R.drawable.button_blue),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Millionerv20Theme {
        WinLoseScreen("Game over!")
    }
}
