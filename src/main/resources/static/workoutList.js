$(document).ready(function () {
    loadWorkouts();
    //searchResult();
});
let but1 = document.getElementById('gbutton').addEventListener('click', searchResult);

function loadWorkouts() {
    var contentRows = $('#contentRows');
    
    $.ajax({
        type: 'GET',
        url: 'https://wger.de/api/v2/exerciseinfo/?format=json',
        success: function(workoutArray) {
            
            $.each(workoutArray.results, function(index, workout){
                
                var name = workout.name;
                
                var muscle = workout.category.name;
                
                var creation_date = workout.creation_date;
                
                //var equipment = workout.equipment[0].name;
                
                //var image = workout.images[0].image;
                
                //var equipment = workout.equipment.name;
                
                var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + muscle + '</td>';
                    row += '<td>' + creation_date + '</td>';
                    //row += '<td>' + equipment + '</td>';
                    //row += '<td>' + image + '</td>';

                    //row += '<td><button type="button" class="btn btn-info">Edit</button></td>';
                    //row += '<td><button type="button" class="btn btn-danger">Delete</button></td>';
                    row += '</tr>';
                //index = index += 1;
                contentRows.append(row);
            })
        
        },
        error: function() {
         $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service. Please try again later.'));
        
        }
    })
    
}

function searchResult(){
    //alert("hi");
    //alert(document.getElementById("newSearch").value);
    //let but1 = 
    //document.getElementById('gbutton').addEventListener('click', //loadWorkouts);
    $("#workoutTable > tbody").html("");
    var contentRows = $('#contentRows');
    
    $.ajax({
        type: 'GET',
        url: 'https://wger.de/api/v2/exerciseinfo/?format=json',
        success: function(workoutArray) {
            
            $.each(workoutArray.results, function(index, workout){
                
                var name = workout.name;
                
                var muscle = workout.category.name;
                
                var creation_date = workout.creation_date;
                
                //var equipment = workout.equipment[0].name;
                
                //var image = workout.images[0].image;
                
                //var equipment = workout.equipment.name;
                //alert(document.getElementById("newSearch").value);
                if(document.getElementById("newSearch").value == name){
                    //alert("hi");
                    var detail = "Workout information:\nName: " + name +"\nMuscle: " + muscle + "\nCreation Date: " + creation_date;
                    var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + muscle + '</td>';
                    row += '<td>' + creation_date + '</td>';
                    //row += '<td>' + equipment + '</td>';
                    //row += '<td>' + image + '</td>';

                    //row += '<td><button type="button" class="btn btn-info">Edit</button></td>';
                    //row += '<td><button type="button" class="btn btn-danger">Delete</button></td>';
                    row += '</tr>';
                
                    contentRows.append(row);
                    alert(detail);
                }
//                else{
//                    var message = "Workout Not Found, Try Again";
//                    alert(message);
//                    break;
//                }
                
//                var row = '<tr>';
//                    row += '<td>' + name + '</td>';
//                    row += '<td>' + muscle + '</td>';
//                    row += '<td>' + creation_date + '</td>';
//                    
//                    row += '</tr>';
//        
//                contentRows.append(row);
            })
        
        },
        error: function() {
         $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service. Please try again later.'));
        
        }
    })
    
    
}
