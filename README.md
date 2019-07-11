# UnitApp
组件化项目
### 01.项目介绍
#### 1.1 项目简介
    - 项目整体架构模式采用：组件化+MVP（MVP和MVC混合）+Rx+Retrofit
  <img src="https://raw.githubusercontent.com/sky8650/UnitApp/master/images_git/base_project.png"  alt="竖"/>
    
### 02.组件化过程
#### 1.1解决组件项目butterknife不能使用的问题
##### 1.1.1:需要在项目下的build.gradle中添加对应的插件（版本最好是9.0以上）
         classpath 'com.jakewharton:butterknife-gradle-plugin:9.0.0-rc1'
##### 1.1.2:在使用到butterknife的model中添加如下
          apply plugin: 'com.jakewharton.butterknife'
         
          api 'com.jakewharton:butterknife:9.0.0-rc1'
          annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0-rc1'
##### 1.1.3： 在对应的activity中将R改成R2
        如果当前的AndroidStudio的版本较高，比如在3.0以上，建议将compileSdkVersion设置为28以上
             
#### 配置Arouter

##### 引入对应的仓库
        "router"  : "com.alibaba:arouter-api:1.4.1",
        "router-compiler" : "com.alibaba:arouter-compiler:1.2.2",

      javaCompileOptions {
            annotationProcessorOptions {
                arguments = [ AROUTER_MODULE_NAME : project.getName() ]
            }
        }
        需要注意的是，每个model以及app都要在build中加上以上代码
#### 切换lib与APP工程目录
     1、设置模块开关：
     isMovieApplication = false  //电影模块开关，false:作为Lib组件存在， true:作为application存在
     2、设置application模式下的applicationId
     ```
       if (rootProject.ext.isMovieApplication){
            //组件模式下设置applicationId
            applicationId "com.xiaolei.unit.app_movie"
        }
        ```
      3、 加载不同模式的manifest文件
      ```
      sourceSets {
        main {
            if (rootProject.ext.isMovieApplication) {
                manifest.srcFile 'src/main/module/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/AndroidManifest.xml'
            }
            jniLibs.srcDirs = ['libs']
        }
    }
    ```
    4、 壳工程根据不同的模式加载lib（可以避免打包和编译一些不必要的组件，提高开发效率）
    ```
    implementation project(':baselibrary')//壳工程只要引入基准包就行
    if (!rootProject.ext.isMovieApplication){
        implementation project(':app_movie')
    }
    ```
