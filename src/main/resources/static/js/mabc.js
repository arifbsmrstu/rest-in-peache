//mabc JS
//Developed By Ariful Islam

$(document).ready(function(){
    
    var totalCategory = 5;
    
    /* Declaring variable to identify html class and id */
    var id_name = new Array(totalCategory); //For ID
    
    for(var t=0;t<totalCategory;t++)
    {
        id_name[t] = "#"+t;
    }
    
    var c_name = new Array(totalCategory);
    
    for(var c=0;c<totalCategory;c++)
    {
        c_name[c] = "."+c;
    }
    
    /* Initializing first active button */
    $("#0").css({"background-color":"silver","color":"red"});
    
    function showImage(tem) // Function to show categorize image
    {      
        
        for(var i=0  ; i<totalCategory ; i++){
            if(tem == id_name[i])
            {
                $(c_name[i]).fadeIn(1000);
                $(tem).css({"background-color":"silver","color":"red"}); //make active
            }
            else
            {
                $(id_name[i]).css({"background-color":"rgba(255,112,51,1)","color":"white"}); //make inactive
            }
        }
    }
    
 
    
     $("#0").click(function(){
        showImage("#0"); //Calling function to show categorize image
        
     });
    
    $("#1").click(function(){ 
        $(".0").hide();
        showImage("#1");    //Calling function to show categorize image
        
    });
    
    $("#2").click(function(){
        $(".0").hide();
        showImage("#2");    //Calling function to show categorize image
    });
    
    $("#3").click(function(){ 
        $(".0").hide();
        showImage("#3");    //Calling function to show categorize image
    });
    
    $("#4").click(function(){  
        $(".0").hide();
        showImage("#4");    //Calling function to show categorize image
    });
    
    
    
});