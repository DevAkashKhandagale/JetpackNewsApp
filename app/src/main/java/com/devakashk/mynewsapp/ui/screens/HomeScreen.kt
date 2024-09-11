import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devakashk.mynewsapp.ui.screens.news.DetailScreen
import com.devakashk.mynewsapp.ui.screens.news.NewsListScreen
import com.devakashk.mynewsapp.ui.screens.news.NewsViewModel
import com.devakashk.mynewsapp.utils.toJsonString
import com.devakashk.mynewsapp.utils.toNewsItem

@Composable
fun MyNavigation(viewModel: NewsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NewsListScreen(viewModel, onItemClicked = { item ->
                viewModel.setSelectedNews(item)
                navController.navigate("detail")
            })
        }
        composable("detail") { backStackEntry ->
            DetailScreen(viewModel)
        }
    }
}