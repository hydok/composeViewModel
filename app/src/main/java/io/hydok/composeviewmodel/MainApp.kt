package io.hydok.composeviewmodel

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import io.hydok.composeviewmodel.ui.theme.ComposeViewModelTheme

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = MainViewModel()
){
    ComposeViewModelTheme {

        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MovieList(viewModel.movieListData)
        }
    }
    viewModel.getMovieList()
}



@Composable
fun MovieList(movieList: List<Movie>){
    var selectedIndex by remember { mutableStateOf(-1) }

    LazyColumn {

        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item, index, selectedIndex) { i ->
                selectedIndex = i
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, index: Int, selectedIndex: Int, onClick: (Int) -> Unit){
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.secondary else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp,
    ) {
        Surface(color = backgroundColor) {

            Row(
                Modifier
                    .padding(2.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = movie.imageUrl,
                        builder = {
                            scale(coil.size.Scale.FILL)
                            placeholder(R.drawable.ic_launcher_foreground)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = movie.desc,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(12.dp)
                        .weight(0.3f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.7f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Box(modifier = Modifier.height(10.dp))
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .border(border = BorderStroke(1.dp, Color.Black), shape = CircleShape)
                            .padding(top = 2.dp, bottom = 2.dp, start = 10.dp, end = 10.dp)
                    )
                    Text(
                        text = movie.desc,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun MovieItem() {
    val movie = Movie(
        "이름",
        "https://howtodoandroid.com/images/coco.jpg",
        "설명",
        "카테고리"
    )

    MovieItem(movie = movie, 0, 0) { i -> }
}