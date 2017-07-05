/**
 * Created by Lachlan on 29/06/2017.
 */
(function ($) {
    $(function () {
        var $currentFrame = $(".frame-output").first();
        var $lastFrame = $(".frame-output").last();
        $currentFrame.nextAll().hide();
        //Hiding the DeckID and cardID in learnCard.scala.html as i didnt know how else to do it
        $(".hidden-fields").hide();
        $currentFrame.nextAll().hide();


        $(this).keyup(function(e){
            if(e.keyCode===32&&$currentFrame.text()!==$lastFrame.text()){
                $currentFrame.hide();
                $currentFrame=$currentFrame.next();
                $currentFrame.show();
                //when the it is the last frame show the response quality options
                if($currentFrame.text()===$lastFrame.text()){
                    $(".response-quality-buttons").show();
                }

            }
            });
    });
})(jQuery);