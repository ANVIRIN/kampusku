package com.example.kampusku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Palet warna kustom yang kontras dan profesional (Deep Navy & Modern Teal)
private val PrimaryBlue = Color(0xFF1B365D)
private val PrimaryContainer = Color(0xFFD6E4F0)
private val PageBackground = Color(0xFFF4F7FB)
private val Ink = Color(0xFF101828)
private val MutedInk = Color(0xFF475467)
private val EmeraldGreen = Color(0xFF027A48)
private val SoftGreen = Color(0xFFD1FADF)
private val DeepTeal = Color(0xFF0E7490)
private val SoftTeal = Color(0xFFCFFAFE)
private val AmberOrange = Color(0xFFB45309)
private val SoftOrange = Color(0xFFFEF3C7)
private val RoseRed = Color(0xFF9F1239)
private val SoftRose = Color(0xFFFFE4E6)
private val PurpleAccent = Color(0xFF6B21A8)
private val SoftPurple = Color(0xFFF3E8FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KampusKuTheme {
                AdaptiveDashboard()
            }
        }
    }
}

@Composable
private fun KampusKuTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = PrimaryBlue,
        onPrimary = Color.White,
        primaryContainer = PrimaryContainer,
        onPrimaryContainer = Ink,
        secondary = DeepTeal,
        background = PageBackground,
        surface = Color.White,
        onSurface = Ink,
        onSurfaceVariant = MutedInk,
        tertiary = EmeraldGreen
    )
    MaterialTheme(colorScheme = colors, content = content)
}

/**
 * Dashboard responsif & adaptif berbasis ukuran layar (Compact, Medium, Expanded).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdaptiveDashboard() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "KampusKu",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Dashboard Akademik & Perkuliahan",
                            style = MaterialTheme.typography.labelMedium,
                            color = MutedInk
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Logo KampusKu",
                        tint = PrimaryBlue,
                        modifier = Modifier.padding(horizontal = 16.dp).size(28.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Ink
                )
            )
        },
        containerColor = PageBackground
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            val currentWidth = maxWidth
            val widthClass = when {
                currentWidth < 600.dp -> "COMPACT · PONSEL"
                currentWidth < 840.dp -> "MEDIUM · LAYAR SEDANG"
                else -> "EXPANDED · TABLET / LAYAR LEBAR"
            }
            val showSideRail = currentWidth >= 840.dp

            Row(modifier = Modifier.fillMaxSize()) {
                if (showSideRail) {
                    DashboardSideRail()
                }
                DashboardContent(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    widthClassLabel = widthClass
                )
            }
        }
    }
}

@Composable
private fun DashboardSideRail() {
    Column(
        modifier = Modifier
            .width(88.dp)
            .fillMaxHeight()
            .background(Color.White)
            .padding(top = 24.dp, start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        SideRailItem(Icons.Filled.Dashboard, "Beranda", selected = true)
        SideRailItem(Icons.Filled.CalendarMonth, "Jadwal", selected = false)
        SideRailItem(Icons.Filled.MenuBook, "Materi", selected = false)
        SideRailItem(Icons.Filled.BarChart, "Nilai", selected = false)
    }
}

@Composable
private fun SideRailItem(icon: ImageVector, label: String, selected: Boolean) {
    val tileColor = if (selected) PrimaryContainer else Color.Transparent
    val iconColor = if (selected) PrimaryBlue else MutedInk
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = tileColor,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.size(width = 56.dp, height = 40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = label, tint = iconColor)
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, color = iconColor)
    }
}

@Composable
private fun DashboardContent(modifier: Modifier, widthClassLabel: String) {
    val metrics = listOf(
        MetricData("Mata Kuliah", "6", "Semester berjalan", Icons.Filled.MenuBook, PrimaryBlue, PrimaryContainer),
        MetricData("Tugas Selesai", "14 / 16", "87% tugas terkumpul", Icons.Filled.AssignmentTurnedIn, EmeraldGreen, SoftGreen),
        MetricData("Jadwal Hari Ini", "3", "Kelas aktif", Icons.Filled.Schedule, DeepTeal, SoftTeal)
    )

    // Minimal 6 kartu mata kuliah (memenuhi dan melebihi syarat minimal 2 kartu mata kuliah)
    val courses = listOf(
        CourseData("Pemrograman Mobile", "Jetpack Compose dan Kotlin", "Pertemuan 7 · UI Adaptif", 0.85f, Icons.Filled.School, PrimaryBlue, PrimaryContainer),
        CourseData("Basis Data Lanjut", "Normalisasi dan Query Optimization", "Tugas Praktikum 4", 0.65f, Icons.Filled.Science, EmeraldGreen, SoftGreen),
        CourseData("Struktur Data & Algoritma", "Graph, Tree, dan Sorting", "Latihan Mandiri Bab 5", 0.90f, Icons.Filled.Code, AmberOrange, SoftOrange),
        CourseData("Kecerdasan Buatan", "Machine Learning & Neural Networks", "Tugas Besar · Minggu Depan", 0.40f, Icons.Filled.Psychology, PurpleAccent, SoftPurple),
        CourseData("Jaringan Komputer", "TCP/IP, Routing & Subnetting", "Quiz Teori Jaringan", 0.75f, Icons.Filled.Router, DeepTeal, SoftTeal),
        CourseData("Komputasi Awan", "Docker, Kubernetes & AWS Basics", "Praktikum Deployment", 0.50f, Icons.Filled.Cloud, RoseRed, SoftRose)
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 250.dp), // minSize diatur 250 dp sesuai persyaratan
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            WindowClassBanner(widthClassLabel)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            WelcomeCard()
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionHeader("Ringkasan Akademik", "Statistik kehadiran, tugas, dan jadwal kuliah.")
        }
        items(metrics) { metric ->
            MetricCard(metric)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionHeader("Daftar Mata Kuliah", "Kartu mata kuliah menyesuaikan grid adaptif (minSize 250dp).")
        }
        items(courses) { course ->
            CourseCard(course)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "KampusKu v1.0 · Dikembangkan untuk Tugas Mandiri Android Development",
                style = MaterialTheme.typography.bodySmall,
                color = MutedInk,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
private fun WindowClassBanner(label: String) {
    Surface(
        color = PrimaryContainer,
        contentColor = Ink,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = PrimaryBlue)
            Spacer(Modifier.width(8.dp))
            Text("Mode Tampilan: $label", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Halo, Mahasiswa!",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Pantau progress studi dan mata kuliahmu secara real-time dengan tampilan adaptif.",
                    color = Color(0xFFE2E8F0),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Surface(shape = CircleShape, color = Color(0xFF334E68), modifier = Modifier.size(58.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Ikon Pendidikan",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 2.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Ink,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MutedInk,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private data class MetricData(
    val label: String,
    val value: String,
    val detail: String,
    val icon: ImageVector,
    val accent: Color,
    val container: Color
)

@Composable
private fun MetricCard(data: MetricData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = data.container,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(data.icon, contentDescription = null, tint = data.accent)
                    }
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    text = data.label,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MutedInk,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = data.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Ink,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = data.detail,
                style = MaterialTheme.typography.bodySmall,
                color = MutedInk,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private data class CourseData(
    val title: String,
    val subtitle: String,
    val note: String,
    val progress: Float,
    val icon: ImageVector,
    val accent: Color,
    val container: Color
)

@Composable
private fun CourseCard(course: CourseData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Surface(
                    color = course.container,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = course.icon,
                            contentDescription = null,
                            tint = course.accent,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Ink,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = course.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MutedInk,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Progres belajar",
                    style = MaterialTheme.typography.labelMedium,
                    color = MutedInk
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${(course.progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = course.accent,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { course.progress },
                modifier = Modifier.fillMaxWidth().height(7.dp),
                color = course.accent,
                trackColor = course.container
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = course.note,
                style = MaterialTheme.typography.labelSmall,
                color = MutedInk,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(name = "Ponsel · Compact", showBackground = true, widthDp = 393, heightDp = 850)
@Composable
private fun PhonePreview() {
    KampusKuTheme { AdaptiveDashboard() }
}

@Preview(name = "Tablet · Expanded", showBackground = true, widthDp = 1000, heightDp = 800)
@Composable
private fun TabletPreview() {
    KampusKuTheme { AdaptiveDashboard() }
}
