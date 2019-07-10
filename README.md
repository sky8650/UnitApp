# UnitApp
组件化项目
### 01.项目介绍
#### 1.1 项目简介
    - 项目整体架构模式采用：组件化+MVP（MVP和MVC混合）+Rx+Retrofit
  <img src="https://raw.githubusercontent.com/sky8650/UnitApp/master/images_git/base_project.png" width="375" alt="竖"/>
  <image src="https://github.com/sky8650/TbsForOffice/blob/master/app/img/device-2019-01-25-141307.png" width="260px"/>
   
    
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
####配置Arouter
##### 引入对应的仓库
        "router"  : "com.alibaba:arouter-api:1.4.1",
        "router-compiler" : "com.alibaba:arouter-compiler:1.2.2",

      javaCompileOptions {
            annotationProcessorOptions {
                arguments = [ AROUTER_MODULE_NAME : project.getName() ]
            }
        }
        需要注意的是，每个model以及app都要在build中加上以上代码
