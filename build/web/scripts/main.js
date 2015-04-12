/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
console.log('Script processed.');

$(document).ready(function(){
    //Apply focus to the first input on a form.
    $('form:not(.filter) :input:visible:enabled:first').focus();

    //Validation for the login form on index.jsp
    $('#login').validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: 'Username is required.',
            password: 'Password is required.'
        }
    });
    
    //Provides behavior for the navigation bar.
    $('#navigation').navPlugin({
            'itemWidth': 150,
            'itemHeight': 30,
            'navEffect': 'fade',
            'speed': 250
    });
    
    //Sets min-height of content div on page load.
    var remainingSpace = $(window).height() - $('#header').height() - $('#navigationMenu').height() - $('#footer').height() - 35;
    console.log('height of content div should be: ' + remainingSpace);
    $('#content').css("min-height", remainingSpace);
    
    //Resizes the page on the resize event.
    $(window).resize(function(){
        var remainingSpace = $(window).height() - $('#header').height() - $('#navigationMenu').height() - $('#footer').height() - 35;
        console.log('height of content div should be: ' + remainingSpace);
        $('#content').css("min-height", + remainingSpace);
    });
});