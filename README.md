# TrendyGifs

Application display trendy gifs using giphy api. Application written on Kotlin programming language with Clean MVVM architecture with databinding. Application has three different app(features), domain and data. Reason of layered structure decrease build time (gradle feature), highly isolated layers and separation of concerns. Currently application has two feature; list trend gif and when clicked on item display it on other page. So I had other features I would change my layer strategy and separate them by feature like login, user-profile, gif-list etc. 

# Development Strategy

  - Use git flow
  - Integrate ktlint and detekt
  - Run static code analyzer tool on pre-push hook command
  - Reference dependencies from single file
  - Plan about layers & package structure
  - Develop data module
  - Develop domain module
  - Develop app module(features)
  
### Libraries

Dillinger uses a number of open source projects to work properly:

* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [Architecture Components](https://developer.android.com/jetpack)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](https://square.github.io/retrofit/)
* [Gson](https://github.com/google/gson)
* [MockK](https://mockk.io)
* [Detekt](https://github.com/arturbosch/detekt)
* [Ktlint](https://github.com/shyiko/ktlint)

I wish to use also Dagger and room  but due to time limitation I couldn't use

### Todos

 - Write MORE Tests
 - Add animation on page changes
 - Implement Dagger
