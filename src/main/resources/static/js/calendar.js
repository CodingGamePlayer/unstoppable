document.addEventListener('DOMContentLoaded', function () {
    $(function () {
        let request = $.ajax({
            url: "/full-calendar/calendar-user", // 변경하기
            method: "GET",
            dataType: "json"
        });

        request.done(function (data) {
            console.log(data); // log 로 데이터 찍어주기.

            var calendarEl = document.getElementById('calendar');
            console.log(calendarEl.innerText);

            var calendar = new FullCalendar.Calendar(calendarEl, {
                /*contentHeight:function(){
                  if(screen.width<800){
                    return 500;
                  }else{
                    return 850;
                  }
                },*/
                // initialDate: '2022-02-07',
                initialView: 'dayGridMonth',
                contentHeight: "auto",
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,listWeek'
                },
                editable: true,
                droppable: true, // this allows things to be dropped onto the calendar
                drop: function (arg) {
                    // is the "remove after drop" checkbox checked?
                    if (document.getElementById('drop-remove').checked) {
                        // if so, remove the element from the "Draggable Events" list
                        arg.draggedEl.parentNode.removeChild(arg.draggedEl);
                    }
                },
                /**
                 * data 로 값이 넘어온다. log 값 전달.
                 */
                events: data
            });
            calendar.render();
        });
        request.fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});
