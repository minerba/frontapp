package com.example.kakaoapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kakaoapp.R
import com.example.kakaoapp.data.CreditHistoryItem
import com.example.kakaoapp.data.CreditViewModel
import com.example.kakaoapp.ui.theme.*

private val HistoryBlue = Color(0xFF4A9EFF)
private val ScoreUp = Color(0xFF4ADE80)
private val ScoreDown = Color(0xFFFF6B6B)

private fun getLoanRate(score: Int) = when {
    score >= 900 -> "최저 연 2.9%"
    score >= 850 -> "최저 연 3.5%"
    score >= 800 -> "최저 연 4.2%"
    score >= 750 -> "최저 연 5.1%"
    score >= 700 -> "최저 연 6.8%"
    else -> "최저 연 10% 이상"
}

@Composable
fun CreditHistoryScreen(
    scoreType: String,
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit = {},
    onTabChange: (String) -> Unit,
    creditViewModel: CreditViewModel = viewModel()
) {
    val state by creditViewModel.state.collectAsStateWithLifecycle()
    val score = if (scoreType == "KCB") state.kcbScore else state.niceScore
    val history = if (scoreType == "KCB") state.kcbHistory else state.niceHistory

    // 히스토리 로드
    LaunchedEffect(scoreType) { creditViewModel.loadHistory(scoreType) }

    // 다이얼로그 상태
    var showAddDialog by remember { mutableStateOf(false) }
    var editingItem by remember { mutableStateOf<CreditHistoryItem?>(null) }
    var deleteTargetId by remember { mutableStateOf<Int?>(null) }

    if (showAddDialog || editingItem != null) {
        HistoryEditDialog(
            agency = scoreType,
            initial = editingItem,
            onDismiss = { showAddDialog = false; editingItem = null },
            onConfirm = { item ->
                if (editingItem != null) creditViewModel.updateHistory(editingItem!!.id, item)
                else creditViewModel.addHistory(item)
                showAddDialog = false; editingItem = null
            }
        )
    }

    if (deleteTargetId != null) {
        AlertDialog(
            onDismissRequest = { deleteTargetId = null },
            containerColor = Color(0xFF1E1E1E),
            title = { Text("삭제 확인", color = TextWhite, fontWeight = FontWeight.SemiBold) },
            text = { Text("이 항목을 삭제하시겠습니까?", color = TextGrayDark) },
            confirmButton = {
                TextButton(onClick = {
                    creditViewModel.deleteHistory(deleteTargetId!!, scoreType)
                    deleteTargetId = null
                }) { Text("삭제", color = ScoreDown) }
            },
            dismissButton = {
                TextButton(onClick = { deleteTargetId = null }) { Text("취소", color = TextGrayDark) }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(BgDark).systemBarsPadding()
    ) {
        // 헤더
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextWhite, modifier = Modifier.size(24.dp))
                }
                Text("조회·변동 내역", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { showAddDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "추가", tint = HistoryBlue, modifier = Modifier.size(24.dp))
                }
                IconButton(onClick = onNavigateToHome) {
                    Icon(Icons.Default.Home, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
                }
            }
        }

        // 탭바
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("KCB", "NICE").forEach { tab ->
                val isSelected = scoreType == tab
                Box(modifier = Modifier.weight(1f).clickable { onTabChange(tab) }, contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            tab,
                            color = if (isSelected) TextWhite else TextGrayDark,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(if (isSelected) TextWhite else Color.Transparent))
                    }
                }
            }
        }
        HorizontalDivider(color = Color(0xFF1F2937))

        if (state.isLoadingHistory) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = HistoryBlue)
            }
            return@Column
        }

        Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(16.dp)) {
            // 대출 금리 배너
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardDark).padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Image(painter = painterResource(R.drawable.icon_credit_score), contentDescription = null, modifier = Modifier.size(40.dp), contentScale = ContentScale.Fit)
                    Column {
                        Text("${score}점으로 가능한", color = HistoryBlue, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        Text("내 대출 최저 금리 보기", color = TextWhite, fontSize = 14.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(getLoanRate(score), color = TextGrayDark, fontSize = 12.sp)
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (history.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("내역이 없습니다", color = TextGrayDark, fontSize = 14.sp)
                        TextButton(onClick = { showAddDialog = true }) {
                            Text("+ 내역 추가하기", color = HistoryBlue)
                        }
                    }
                }
            } else {
                // 연도별 그룹핑
                val grouped = history.groupBy { it.year }.toSortedMap(compareByDescending { it })
                grouped.forEach { (year, items) ->
                    Text(year, color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(modifier = Modifier.padding(start = 20.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        items.forEach { item ->
                            Box {
                                Box(modifier = Modifier.offset(x = (-18).dp, y = 6.dp).size(8.dp).clip(CircleShape).background(Color(0xFF4B5563)))
                                HistoryItemRow(
                                    item = item,
                                    onEdit = { editingItem = item },
                                    onDelete = { deleteTargetId = item.id }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun HistoryItemRow(item: CreditHistoryItem, onEdit: () -> Unit, onDelete: () -> Unit) {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
            Column(modifier = Modifier.weight(1f)) {
                Text(item.date, color = TextGrayDark, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(2.dp))
                if (item.type == "loan") {
                    Text(item.title ?: "대출 잔액 변동", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(item.description ?: "", color = TextGray, fontSize = 14.sp)
                } else {
                    val isUp = (item.changeAmount ?: 0) > 0
                    Row {
                        Text("신용점수 ", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Text("${Math.abs(item.changeAmount ?: 0)}점", color = if (isUp) ScoreUp else ScoreDown, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Text(" ${if (isUp) "상승" else "하락"}", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text("${item.agency} ${item.scoreFrom}점 → ${item.scoreTo}점", color = TextGray, fontSize = 14.sp)
                }
            }
            Row {
                IconButton(onClick = onEdit, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Edit, contentDescription = "수정", tint = TextGrayDark, modifier = Modifier.size(16.dp))
                }
                IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "삭제", tint = ScoreDown.copy(alpha = 0.7f), modifier = Modifier.size(16.dp))
                }
            }
        }
        if (item.type == "loan" && item.hasCard) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardDark).padding(16.dp)) {
                Text("대출 잔액을 확인해보세요", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("상환 후 잔액을 확인하고 다음 상환일을 준비하세요.", color = TextGray, fontSize = 14.sp, lineHeight = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = {},
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                ) { Text("자세히 보기", fontSize = 14.sp) }
            }
        }
    }
}

@Composable
private fun HistoryEditDialog(
    agency: String,
    initial: CreditHistoryItem?,
    onDismiss: () -> Unit,
    onConfirm: (CreditHistoryItem) -> Unit
) {
    var type by remember { mutableStateOf(initial?.type ?: "loan") }
    var year by remember { mutableStateOf(initial?.year ?: "2026년") }
    var date by remember { mutableStateOf(initial?.date ?: "") }
    var title by remember { mutableStateOf(initial?.title ?: "대출 잔액 변동") }
    var description by remember { mutableStateOf(initial?.description ?: "") }
    var hasCard by remember { mutableStateOf(initial?.hasCard ?: false) }
    var changeAmount by remember { mutableStateOf(initial?.changeAmount?.toString() ?: "") }
    var scoreFrom by remember { mutableStateOf(initial?.scoreFrom?.toString() ?: "") }
    var scoreTo by remember { mutableStateOf(initial?.scoreTo?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        title = { Text(if (initial != null) "내역 수정" else "내역 추가", color = TextWhite, fontWeight = FontWeight.SemiBold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // 타입 선택
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("loan" to "대출", "score" to "점수변동").forEach { (value, label) ->
                        FilterChip(
                            selected = type == value,
                            onClick = { type = value },
                            label = { Text(label, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = BlueAccent,
                                selectedLabelColor = Color.White,
                                containerColor = Color(0xFF2A2A2A),
                                labelColor = TextGrayDark
                            )
                        )
                    }
                }
                DialogField("연도 (예: 2026년)", year) { year = it }
                DialogField("날짜 (예: 3. 27.)", date) { date = it }

                if (type == "loan") {
                    DialogField("제목", title) { title = it }
                    DialogField("설명", description) { description = it }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = hasCard, onCheckedChange = { hasCard = it }, colors = CheckboxDefaults.colors(checkedColor = BlueAccent))
                        Text("잔액 확인 카드 표시", color = TextGrayDark, fontSize = 13.sp)
                    }
                } else {
                    DialogField("변동 점수 (예: -8 또는 5)", changeAmount, KeyboardType.Number) { changeAmount = it }
                    DialogField("이전 점수", scoreFrom, KeyboardType.Number) { scoreFrom = it }
                    DialogField("이후 점수", scoreTo, KeyboardType.Number) { scoreTo = it }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val item = CreditHistoryItem(
                    id = initial?.id ?: 0,
                    agency = agency,
                    type = type,
                    year = year,
                    date = date,
                    title = if (type == "loan") title else null,
                    description = if (type == "loan") description else null,
                    hasCard = if (type == "loan") hasCard else false,
                    changeAmount = if (type == "score") changeAmount.toIntOrNull() else null,
                    scoreFrom = if (type == "score") scoreFrom.toIntOrNull() else null,
                    scoreTo = if (type == "score") scoreTo.toIntOrNull() else null
                )
                onConfirm(item)
            }) { Text("저장", color = BlueAccent) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("취소", color = TextGrayDark) }
        }
    )
}

@Composable
private fun DialogField(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp, color = TextGrayDark) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = TextWhite,
            unfocusedTextColor = TextWhite,
            focusedBorderColor = BlueAccent,
            unfocusedBorderColor = Color(0xFF4B5563)
        )
    )
}
