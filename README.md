# TrendyGifs

Application display trendy gifs using giphy api. Application written on Kotlin programming language with Clean MVVM architecture with databinding. Application has three different app(features), domain and data. Reason of layered structure decrease build time (gradle feature), highly isolated layers and separation of concerns. Currently application has two feature; listing trend gifs and show fullscreen of clicked itemon other page. So If I had other features I would change my layer strategy and separate them by feature like login, user-profile, gif-list etc. 

Portrait | Landscape |
--- | --- |
*![Portrait](https://github.com/gokhanaliccii/TrendyGifs/blob/develop/art/ui_portrait.jpg)*|![Landscape loaded](https://github.com/gokhanaliccii/TrendyGifs/blob/develop/art/ui_landscape.jpg)

## Development Strategy

  - Use git flow
  - Integrate ktlint and detekt
  - Run static code analyzer tool on pre-push hook command
  - Reference dependencies from single file
  - Plan about layers & package structure
  - Develop data module
  - Develop domain module
  - Develop app module(features)
  
  ## Architecture
app_layer | domain_layer | data_layer |
--- | --- | --- |
*![app](https://github.com/gokhanaliccii/TrendyGifs/blob/develop/art/layer_app.png)*|*![domain](https://github.com/gokhanaliccii/TrendyGifs/blob/develop/art/layer_domain.png)*|*![data](https://github.com/gokhanaliccii/TrendyGifs/blob/develop/art/layer_data.png)*|
  
Application separeted on the app, domain and data layers. Data layer communicate with giphy api to provide trend gifs and cache service response. Domain layer interact with data layer to provide required information for presentation layer. 

  
### Libraries

* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [Architecture Components](https://developer.android.com/jetpack)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](https://square.github.io/retrofit/)
* [Gson](https://github.com/google/gson)
* [MockK](https://mockk.io)
* [Detekt](https://github.com/arturbosch/detekt)
* [Ktlint](https://github.com/shyiko/ktlint)

I wish to use also Dagger and room  but due to time limitation I couldn't use

### How to develop custom GifLoader

Imageview natively doesn't support gifs so to show gif we need to do custom implementation. To display gifs we have several options like using custom libraries(glide,picasso, fresco etc), implementing custom webview or decode gifview and animate each frame on imageview. [Gif decoding approach](https://stackoverflow.com/a/11736861/2608485), [webview approach](https://stackoverflow.com/a/38810732/2608485). If I had enough time I would apply gif decoding approach with using HandlerThread  

### Known Issues

- I should save/restore ui object states on onSaveInstance/onCreate (Process death possibility)
- I passed context to data layer from ui layer to access sharedPreference which we use save service response. I dislike this approach but I don't enough time to implement better solution like room or realm.
- I should handle cache mechanism better like expire 

### Todos

 - Write MORE Tests
 - Add animation on page changes
 - Implement Dagger
 - Custom Gif Loader
 - Handle exceptions
