# Project_05 - Translation App
<span style="font-size: smaller;"><strong>Ashley Steitz and Jacob Fritz worked on this as partners</strong></span>

---
<span style="font-size: smaller;"><strong> Description </strong> </span>
In our efforts to complete Project 05 we implemented an interactive Ui (User Interface) that allows the user to selcet a language and translate into one of the 3 options. 

This app allows for 3 Languages to Translate From:
- English
- Spanish
- German

This app allows for 4 Languages to Translate From:
- English
- Spanish
- German

To begin we integrated a ML Kit provided in the PDF to help translate the words entered by the user and passed in the intended language to translate into through a view group. This project used View Groups 
across both the Fragments, Kotlin Files, and XML to assign and gather the text information entered by the user. 

The depending on which radio button is selected from the source and target translation languages, we set the according languages and call the TranslateLanguage() function with the provided languages. In the
Fragment we handle the input text through a TextView and display the translated language above the inputted language. This is done as the user enters the words, so the previously translated part 
may change as you type in new words to get a more accurate picture of what you are trying to say in the target language. 


## Functionality
'*' indicates tested in GIF  
The following **required** functionality is completed:
<br>
Safeargs and View Groups were implemented to transfer data from MainActivity.kt to Fragment1.kt. This allows us to gather the user data and translate it in real time!

**Demonstrated** 4753
* [English] -> [Spanish] [Typed: ] [Recieved: ] [ctrl +  a 'delete']
* [English] -> [German]  [Typed: ] [Recieved: ] [ctrl +  a 'delete']
* [Spanish] -> [German]  [Typed: ] [Recieved: ] [ctrl +  a 'delete']
* [Spanish] -> [English] [Typed: ] [Recieved: ] [ctrl +  a 'delete']
* [German] -> [Spanish]  [Typed: ] [Recieved: ] [ctrl +  a 'delete']
* [German] -> [English]  [Typed: ] [Recieved: ] [END]


---
## Video Walkthrough
Watch a demonstration of each language combination in the gif available on Github
Here's a walkthrough of a few translations:
<img src='https://github.com/jfritz25/Project3/blob/master/app/src/main/java/com/example/project3/RecordingProject3.gif' title='Project3 Video Walkthrough' width='50%' height = '50%' alt='Video Walkthrough' />

GIF created with [CloudConvert](https://cloudconvert.com/).

## Notes
UI Challenges:
- Passing the data from a fragment textview part into the nragment section in the MainActivity.xml
- Setting up the fragment section

Backend Challenges:
- Implementing a way to pass items through view groups as opposed to the R.id
- Understanding the inflating of view groups
- Using the viewgroups and the binding and how it works the same with fragments as well as kotlin files
- Adding the proper additions to the gradle files and the needed declarations of viewgroups and bindings in the onCreate()

## License

    Copyright [2023] [Ashley Steitz, Jacob Fritz]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.