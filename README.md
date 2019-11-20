# UnitApp
组件化项目
### 01.项目介绍
#### 1.1 项目简介
    - 项目整体架构模式采用：组件化+MVP（MVP和MVC混合）+Rx+Retrofit
  <img src="https://raw.githubusercontent.com/sky8650/UnitApp/master/images_git/base_project.png"  alt="竖"/>
    
### 02.组件化过程
#### 2.1解决组件项目butterknife不能使用的问题
##### 2.1.1:需要在项目下的build.gradle中添加对应的插件（版本最好是9.0以上）
         classpath 'com.jakewharton:butterknife-gradle-plugin:9.0.0-rc1'
##### 2.1.2:在使用到butterknife的lib中添加如下
          apply plugin: 'com.jakewharton.butterknife'
         
          api 'com.jakewharton:butterknife:9.0.0-rc1'
          annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0-rc1'
##### 2.1.3： 在对应的activity中将R改成R2
        如果当前的AndroidStudio的版本较高，比如在3.0以上，建议将compileSdkVersion设置为28以上
##### 2.1.4 注意在每个module中都需要添加以下引用，否则可能会出现找不到id的问题
      annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0-rc1'
      apply plugin: 'com.jakewharton.butterknife'
             
#### 2.2 配置Arouter

##### 引入对应的仓库
        "router"  : "com.alibaba:arouter-api:1.4.1",
        "router-compiler" : "com.alibaba:arouter-compiler:1.2.2",

      javaCompileOptions {
            annotationProcessorOptions {
                arguments = [ AROUTER_MODULE_NAME : project.getName() ]
            }
        }
        需要注意的是，每个model以及app都要在build中加上以上代码
#### 2.3 切换lib与APP工程目录
     1、设置模块开关：
     isMovieApplication = false  //电影模块开关，false:作为Lib组件存在， true:作为application存在
     2、设置application模式下的applicationId
       if (rootProject.ext.isMovieApplication){
            //组件模式下设置applicationId
            applicationId "com.xiaolei.unit.app_movie"
        }
      3、 加载不同模式的manifest文件
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
    4、 壳工程根据不同的模式加载lib（可以避免打包和编译一些不必要的组件，提高开发效率）
    implementation project(':baselibrary')//壳工程只要引入基准包就行
    if (!rootProject.ext.isMovieApplication){
        implementation project(':app_movie')
    }
  #### 2.4其他配置
       为了便于进行对各个组件的版本管控，可以将版本控制统一放在config.gradle文件中
       apply from: "config.gradle"
        version = [
            androidSupportSdkVersion: "28.0.0",
            retrofitSdkVersion      : "2.2.0",
            butterknifeSdkVersion   : "8.5.1",
            rxlifecycle2SdkVersion  : "2.0.1"
    ]
    组件使用： 
    compileSdkVersion rootProject.ext.android["compileSdkVersion"]
    buildToolsVersion rootProject.ext.android["buildToolsVersion"]
    
   #### 2.5AndroidStudio SSL peer shut down incorrectly 问题
   
```
 AndroidStudio 编译时出现如下问题 SSL peer shut down incorrectly 或者某些jar包下载不下来，
    一般是因为墙的原因导致的。这时候我们就需要配置镜像来解决这个问题。（为了提高jar包的下载速度也可以配置）
    配置的方法就是在根build.gradle中添加镜像仓库，一般我们选择阿里的 http://maven.aliyun.com/nexus/content/groups/public/完整的如下所示
buildscript {

    repositories {
        google()
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
    }
}

allprojects {
    repositories {
        google()
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```
这里需要注意要将jcenter放到最后一个，因为他就是那个下载慢，或者报错的罪魁祸首


    
    
    
    

    
    
    
       
       
       

