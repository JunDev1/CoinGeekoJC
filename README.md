Push # 1 При первичной инициализации были добавлены библиотеки retforit и Kotlinx Serialization  

Push # 2 Создание пользовательского интерфейса без логической связи

1. Код
    > dasdad
2. 
```
@Composable
fun ListCryptoScreen() {
    var selectedCurrency by remember { mutableStateOf("USD") }
    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp
            ) {
                Column {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(1f),
                        title = {
                            Text(text = stringResource(R.string.title_list_crypto))
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val color = when (selectedCurrency) {
                            "USD" -> InputChipDefaults.inputChipColors(
                                selectedContainerColor = Color(
                                    0xFFFFA500
                                )
                            )

                            "RUB" -> InputChipDefaults.inputChipColors(
                                selectedContainerColor = Color(
                                    0xFFFFA500
                                )
                            )

                            else -> {
                                InputChipDefaults.inputChipColors(
                                    disabledContainerColor = Color(
                                        0xFFE0E0E0
                                    )
                                )
                            }
                        }
                        InputChip1(
                            modifier = Modifier.padding(start = 16.dp),
                            selected = selectedCurrency == "USD",
                            onClick = { selectedCurrency = "USD" },
                            label = { Text(text = stringResource(R.string.usd)) },
                            colors = color

                        )
                        InputChip1(
                            selected = selectedCurrency == "RUB",
                            onClick = { selectedCurrency = "RUB" },
                            label = { Text(text = stringResource(R.string.rub)) },
                            colors = color
                        )
                    }
                }
            }
        }

    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(20) {
                CardInfo()
            }
        }

    }
}
```
```
@Composable
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
```

![image](https://github.com/user-attachments/assets/f38c3e72-409f-468f-af34-874d4084ccfb)

Push # 3 Установка связи с сервером и тестовый запуск проверки соединения через Log.d("MainActivity", endpoint.toString())
![Снимок экрана (18)](https://github.com/user-attachments/assets/63693b00-31e3-45b5-8523-a1419b9f8510)
