import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diagnosaallapps.HomeScreen
import com.example.diagnosaallapps.Profile
import com.example.diagnosaallapps.R
import com.example.diagnosaallapps.Upload
import com.example.diagnosaallapps.ui.navigation.Screen

@Composable
fun DiagnoseAllApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    Scaffold(bottomBar = { BottomBar(modifier, navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.History.route) {
                Text(text = "History")
            }
            composable(Screen.Upload.route) {
//                Text(text = "Upload Here")
                context.startActivity(Intent(context, Upload::class.java))
            }   
            composable(Screen.Profile.route) {
                context.startActivity(Intent(context, Profile::class.java))
//                AndroidView(
//                    factory = {
//                        View.inflate(it, R.layout.activity_profile, null)
//                    },
//                    modifier = Modifier.fillMaxSize(),
//                    update = {
//
//                    }
//                )
            }
        }
    }
}