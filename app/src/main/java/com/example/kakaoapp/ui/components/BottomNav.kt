package com.example.kakaoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.theme.*

data class NavItem(val label: String, val icon: ImageVector)

@Composable
fun BottomNavBar(selectedIndex: Int, onItemSelected: (Int) -> Unit, modifier: Modifier = Modifier) {
    val items = listOf(
        NavItem("홈", Icons.Default.Home),
        NavItem("혜택", Icons.Default.CardGiftcard),
        NavItem("결제", Icons.Default.CreditCard),
        NavItem("자산", Icons.Default.AccountBalanceWallet),
        NavItem("증권", Icons.Default.BarChart),
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF1A1A1A))
            .padding(top = 8.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEachIndexed { index, item ->
            val isActive = index == selectedIndex
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    item.icon,
                    contentDescription = item.label,
                    tint = if (isActive) TextWhite else Color(0xFF666666),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    item.label,
                    color = if (isActive) TextWhite else Color(0xFF666666),
                    fontSize = 10.sp,
                    fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
                )
            }
        }
    }
}
