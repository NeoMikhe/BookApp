package com.example.bookapp.books

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.books.data.Books
import com.example.bookapp.books.data.DataProvider
import com.example.bookapp.books.resenas.MainResenaActivity
import com.example.bookapp.books.ui.theme.purple500
import com.example.bookapp.books.resenas.Resenas

@Composable
fun BookScreen(books: Books, onNavIconPressed: () -> Unit = { }) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ProfileHeader(
                        scrollState,
                        books,
                        this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(books, this@BoxWithConstraints.maxHeight)
                }
            }
            BookFab(
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    books: Books,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id = books.bookImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun ProfileContent(books: Books, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(books)

        ProfileProperty(stringResource(R.string.tags), books.tags)

        ProfileProperty(stringResource(R.string.description), books.description)

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    books: Books
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            books = books,
            modifier = Modifier.baselineHeight(32.dp)
        )
    }
}

@Composable
private fun Name(books: Books, modifier: Modifier = Modifier) {
    Text(
        text = books.title,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}

@Composable
fun BookFab(extended: Boolean, modifier: Modifier = Modifier) {
    val mContext : Context = LocalContext.current
    FloatingActionButton(
        onClick = { mContext.startActivity(Intent(mContext, MainResenaActivity::class.java)) },
        modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = purple500,
        contentColor = Color.White
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = stringResource(R.string.ver_resena)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.ver_resena),
                )
            },
            extended = extended

        )
    }
}

@Preview
@Composable
fun ProfilePreview() {
    val books = DataProvider.book
    BookScreen(books = books)
}