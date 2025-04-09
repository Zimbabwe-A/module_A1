package com.example.module_a1.page.main_page.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module_a1.ui.theme.Gray100
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.ui.text.style.TextOverflow
import com.example.module_a1.page.main_page.model.Product


import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module_a1.page.main_page.viewmodel.CatalogViewModel

@Composable
fun CatalogPage(viewModel: CatalogViewModel = viewModel()) {
    val searchQuery = viewModel.searchQuery
    val filteredProducts = viewModel.filteredProducts

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(horizontal = 8.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchChange,
            prefix = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.Gray,
                )
            },
            label = {
                Text(
                    "Search...",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                    )
                )
            },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
            )
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp)
        ) {
            items(filteredProducts.size) { index ->
                ProductItem(product = filteredProducts[index])
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
            }

            // Название и описание
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = product.name, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
        }
    }
}