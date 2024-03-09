
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.startmenu.ui.theme.StartMenuTheme

@Composable
fun MenuMillionera(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel?,
    nv: NavController?
) {
    Box (
        modifier = modifier
    ) {


        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,


            )
        Image(
            painter = painterResource(id = R.drawable.logo_with_text),
            contentDescription = "Кто хочет стать миллионером?",
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 270.dp),
            contentScale = ContentScale.Fit,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 690.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.End
        ) {
            CustomImageButton()
        }
    }

}

@Composable
fun CustomImageButton() {
    Box(
        modifier = Modifier
            .offset(-13.dp)
            .size(32.dp) // Размер
            .clickable { println("Start game") }
    ) {
        Image(
            painter = painterResource(id = R.drawable.help),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Composable
fun CustomButton() {
    Box(
        modifier = Modifier
            .width(311.dp)
            .height(62.dp)
            .clickable { println("Start game") }
    ) {
        Image(
            painter = painterResource(id = R.drawable.new_game_button),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

    }
}