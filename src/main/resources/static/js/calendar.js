document.addEventListener('DOMContentLoaded', function () {
    $(function () {
        let request = $.ajax({
            url: "/api/main/calendar", // 변경하기
            method: "GET",
            dataType: "json"
        });

        request.done(function (data) {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                /*contentHeight:function(){
                  if(screen.width<800){
                    return 500;
                  }else{
                    return 850;
                  }
                },
                initialDate: '2022-02-07',*/
                displayEventTime: false,
                initialView: 'dayGridMonth',
                contentHeight: "auto",
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,listWeek'
                },

                locale: "ko",

                editable: false,
                droppable: false, // this allows things to be dropped onto the calendar
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
