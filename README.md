# Color Detection

The primary objective of this project is to design, implement, and evaluate an android application that assists color-blind individuals to be able to identify colors they are not able to see.
Research says that 1 in 12 men is color blind while only 1 in 200 women have the condition.
95% of the color-blind community are men
98% of those with color blindness have red-green blindness
This will also provide a simple and beneficial tool for individuals that work with colors such as painters and designers, to improve their productivity rates by locating colors quickly and easily.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/58ea0df1-ad59-419e-a5cf-215d6f50742f)

## System Design
1) The color detector application enables users to identify colors in real-time using the camera on their mobile device. Alternatively, they can upload or take a photo to detect colors in the photograph.

2) The User Interface(UI) includes a screen that displays the selected picture.

3) It also consists of a camera button which presents two options:
Camera icon: allows users to take a photo.
Gallery icon: enables you to choose an existing picture from your gallery.

4) The app also allows users to crop the picture to obtain color results for a specific part of the image.
5) The camera feature in the app includes a magnifying glass option to zoom and capture a picture.
6) After the user selects a picture, the app provides a button labeled "Generate Result." Once clicked, the button generates the result, i.e., the HEX code of the color followed by the name of the color.



## Color Detection in Different Scenario

1. CASE : 1

When the picture has just one color 
--> The app returns the color in the picture selected.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/2e0ad758-3118-4ee7-bddc-312bcc6f8cd9)


2. CASE : 2


When the picture has equal dominance(equal spit) of two colors.
--> In such rare cases, the app tends to choose one of the two colors.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/85cfe01d-ddce-427d-8f68-1b3873f8ec00)


3. CASE : 3

When the picture has more than one color 
--> The app chooses the color which is dominant in the picture selected.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/d0718110-0c89-4a8c-b12f-da1a25ebb102)



## Application Design 
1. The design of the application enables to capture images or open saved images in the gallery and generate their dominant color.

2. This functionality is performed by a dedicated floating action button. After selecting an image, the user can crop it and select the portion they want to know the color of. The image will then be saved and displayed in the center of the screen.

3. To generate the dominant color of the selected portion, the user needs to click on the "Generate Result" button. The ColorFinder Java class will be called to perform the color generation functionality, and the dominant color will be displayed below the button.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/ffa3d063-7cc9-4fa8-bcaa-4f9448175535)


## Image Capturing

1. We have used the ImagePicker library, which can pick an image from the gallery or capture image using the device camera.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/a40a5c7e-c3b2-4d6e-affa-cf3c008b67bf)

3. Crop Image ( Crop image based on provided aspect ratio or let the user use)

4. Retrieve image as uri object.  

5. Handling runtime permission for camera.

6. Users can select between the Camera option and Gallery option; here, we have shown the process if the user intends to choose the camera option and capture real-time images.
Otherwise, if the user selects the Gallery option, the application has provided permission to access the local storage drive and select any available image from there.

![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/19dbf183-ac5e-4dcc-8375-310cc2a2580e)
![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/5080a6a0-8d8c-47a5-b349-87eaa58ba839)
![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/4793898e-c3c2-49d3-bd62-b9db50d19ee0)



## Energy Consumption
![image](https://github.com/rahulkumarmmmut/colordetection/assets/87722928/a47253ce-1afa-4940-859d-cee885471a25)


## License

[MIT](https://choosealicense.com/licenses/mit/)



## References:
1. https://stackoverflow.com/a/28673282   - image to hexadecimal of most dominant pixel

2. https://github.com/Dhaval2404/ImagePicker - picking image from camera / gallery with cropping

3. https://square.github.io/retrofit/  - android library

4. https://www.thecolorapi.com/docs#schemes-generate-scheme-get  - API Calling

