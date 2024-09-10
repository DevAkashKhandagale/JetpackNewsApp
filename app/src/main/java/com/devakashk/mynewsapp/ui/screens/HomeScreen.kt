import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devakashk.mynewsapp.ui.screens.news.DetailScreen
import com.devakashk.mynewsapp.ui.screens.news.NewsListScreen
import com.devakashk.mynewsapp.ui.screens.news.NewsViewModel
import com.devakashk.mynewsapp.utils.toNewsItem

@Composable
fun MyNavigation(viewModel: NewsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NewsListScreen(viewModel, onItemClicked = { item ->
                Log.d("My Navigation", "MyNavigation: ${item.title}")
                val itemsString = Uri.encode(item.toString())
                Log.d("MyNavigation", "MyNavigation: $itemsString")
                navController.navigate("detail?item=$itemsString")
            })
        }
        composable("detail/{item}") { backStackEntry ->
            val itemsString = backStackEntry.arguments?.getString("item") ?: ""
            val item = itemsString.toNewsItem()
            Log.d("My Navigation", "MyNavigation: ${item.title}")
            DetailScreen(item)
        }
    }
}