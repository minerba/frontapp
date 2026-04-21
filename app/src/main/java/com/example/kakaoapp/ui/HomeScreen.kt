package com.example.kakaoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.components.*
import com.example.kakaoapp.ui.theme.*

@Composable
fun HomeScreen(onNavigateToCredit: () -> Unit = {}) {
    var selectedNavIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { TopBannerCard() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { PayMoneySection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { ServiceGrid(onCreditClick = onNavigateToCredit) }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { AdBanner() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { CreditScoreSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { PayPointSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { BalanceFightSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { FortuneSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { PsychTestSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { PayAttentionSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { MiniGameSection() }
                Box(modifier = Modifier.padding(horizontal = 12.dp)) { RecommendSection() }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        BottomNavBar(
            selectedIndex = selectedNavIndex,
            onItemSelected = { selectedNavIndex = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("할일", color = TextWhite, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextWhite, modifier = Modifier.size(16.dp))
            Box(
                modifier = Modifier.size(18.dp).clip(CircleShape).background(RedBadge),
                contentAlignment = Alignment.Center
            ) {
                Text("1", color = TextWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold, lineHeight = 11.sp)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Search, contentDescription = "검색", tint = TextWhite, modifier = Modifier.size(22.dp))
            Box {
                Icon(Icons.Default.Notifications, contentDescription = "알림", tint = TextWhite, modifier = Modifier.size(22.dp))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(RedBadge).align(Alignment.TopEnd))
            }
            Icon(Icons.Default.Menu, contentDescription = "메뉴", tint = TextWhite, modifier = Modifier.size(22.dp))
        }
    }
}

@Composable
private fun NewFeedButton(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFF2D2D2D))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text("새 피드", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = TextWhite, modifier = Modifier.size(16.dp))
        }
    }
}
