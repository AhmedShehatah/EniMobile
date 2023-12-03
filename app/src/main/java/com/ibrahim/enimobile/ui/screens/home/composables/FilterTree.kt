package com.ibrahim.enimobile.ui.screens.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

data class TreeNode(
    val name: String,
    val children: List<TreeNode> = emptyList(),
    var isExpanded: MutableState<Boolean> = mutableStateOf(false),
    var depth: Int = 0
)


@Composable
fun FilterTree() {
    val treeData = generateSampleData()
    LazyColumn {
        items(treeData) { treeNode ->
            TreeNodeItem(treeNode)
        }
    }

}

@Composable
fun TreeNodeItem(treeNode: TreeNode) {
    Column {
        Row(
            modifier = Modifier
                .padding(start = treeNode.depth * 16.dp)
                .clickable {
                    treeNode.isExpanded.value = !treeNode.isExpanded.component1()

                }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (treeNode.children.isNotEmpty()) {
                if (treeNode.isExpanded.component1()) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                } else {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                }
            } else {
                Spacer(modifier = Modifier.width(24.dp)) // Adjust for indentation
            }

            // Display node content (you can customize this part based on your data)

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = treeNode.name)
        }

        // Recursively display child nodes if expanded
        if (treeNode.isExpanded.component1()) {
            treeNode.children.forEach { childNode ->
                childNode.depth = treeNode.depth + 1
                TreeNodeItem(childNode)
            }
        }
    }
}

private fun generateSampleData(): List<TreeNode> {
    return listOf(
        TreeNode(
            "Folder 1", listOf(
                TreeNode("File 1.1"),
                TreeNode("File 1.2"),
                TreeNode(
                    "Folder 1.3", listOf(
                        TreeNode("File 1.3.1"),
                        TreeNode(
                            "File 1.3.2",
                            listOf(TreeNode("File name 5"))
                        )
                    )
                )
            )
        ),

        TreeNode(
            "Folder 3", listOf(
                TreeNode("File 3.1"),
                TreeNode(
                    "Folder 3.2", listOf(
                        TreeNode("File 3.2.1"),
                        TreeNode("File 3.2.2")
                    )
                ),
                TreeNode("File 3.3")
            )
        )
    )
}