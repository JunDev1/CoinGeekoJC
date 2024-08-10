Push # 1 При первичной инициализации были добавлены библиотеки retforit и Kotlinx Serialization  

Push # 2 Создание пользовательского интерфейса без логической связи
    <@Composable
fun LoadingScreen() {
    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp
            ) {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.name_currency)) },
                    navigationIcon = {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 32.dp)
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CircularProgressIndicator(
                color = Color(0xFFFFA500) // Цвет индикатора (оранжевый)
            )
        }
    }
}
>

![image](https://github.com/user-attachments/assets/f38c3e72-409f-468f-af34-874d4084ccfb)

Push # 3 Установка связи с сервером и тестовый запуск проверки соединения через Log.d("MainActivity", endpoint.toString())
![Снимок экрана (18)](https://github.com/user-attachments/assets/63693b00-31e3-45b5-8523-a1419b9f8510)
