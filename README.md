# BasicNeuralNetwork
Basic Neural Network design following Veritasium and Tsoding videos.

Basic Neural Network design following:
* Veritasium: "Future Computers Will Be Radically Different" - https://www.youtube.com/watch?v=GVsUOuSjvcg
* Tsoding Daily: "First Ancient Neural Network in C" - https://www.youtube.com/watch?v=WEk_grxrCcg

Interesting concepts, but I figured the the same could be done in Java using a simpler design.

Tsoding used a PPM formatted image generated randomly in C as his shapes. To keep my version simple, I used a 2D array of 1's and 0's. The neural network should be able to take any canvas using a weights grid of the same dimensions and determine if the shape on it is a rectangle (0) or a circle (1).

In order to get a weight grid that can do this, we have to train it. If we give it a rectangle and our BNN says its a circle, we should subtract the grid from the weights. On the other hand, if we give it a circle and it figures a rectangle, we should add the two grids.

 Both Tsoding's model and my own create a lack luster result of about 57% accuracy on a good day.
 
 In order to fix this low success rate (relatively, since an untrained model should have about a 50% success rate), I plan on following 3Blue1Brown's youtube series on Neural Networks: https://youtube.com/playlist?list=PLZHQObOWTQDNU6R1_67000Dx_ZCJB-3pi
 
 While this is simple problem to solve analytically (if a grid has more than 4 "straight lines", its a circle), designing the code in this form allows me to take it a step further if I can get it to a relatively accurate success rate (above 95%). Then I can redesign the code to find cats or dogs.
