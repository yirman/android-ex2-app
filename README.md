
# BlogEx2 App

## Clean Code Architecture +  MVVM

![71248284-78d00800-2340-11ea-9077-080e29a8c918](https://user-images.githubusercontent.com/15848644/198345114-43fb9096-5a03-44ae-9368-a273d1a181e2.png)

## LocalNetworkBoundResource Caching Strategy

Algorithm for providing data by either retrieving sufficiently recent data from a local cache, or loading the latest data from the network.

![1_MBgpG69jXTz8PXBdB_37wg](https://user-images.githubusercontent.com/15848644/198362109-d07e7afa-cb74-4e83-bf82-b1e068397f29.png)

## Main Libraries Used

- Coroutines 🚀 https://developer.android.com/kotlin/coroutines

- Retrofit 📲  https://square.github.io/retrofit/

- Room  💾  https://developer.android.com/jetpack/androidx/releases/room

- Hilt 💉  https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419

- Lottie 🎁 https://lottiefiles.com/blog/working-with-lottie/getting-started-with-lottie-animations-in-android-app

- Kotlin 🥇 (https://developer.android.com/kotlin)


## Project Structure

- domain: Containing all use cases, and repositories for business logic. 📡

- data: Containing both local and network sources, repositories implementations and use case implementations. 🖨️

- di: Containing modules for dependency injection, e.g Modules for data sources and use cases. 💉

- presentation: Containing viewmodels, activities and recyclerview's adapters. 👀

- util: Containing utility classes and localNetworkBoundResource function. 👷🏼‍♂️

<br>
