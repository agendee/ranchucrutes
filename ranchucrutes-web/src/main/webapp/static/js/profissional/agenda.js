$('#calendar').fullCalendar({
      header: {
          left: 'prev,next today',
          center: 'title',

          right: 'month,agendaWeek,agendaDay'
      },
      defaultView: 'agendaWeek',
      defaultDate: '2015-02-12',
      lang: "pt-br",
      buttonIcons: false, // show the prev/next text
      weekNumbers: true,
      editable: true,
      slotDuration:"00:05",
      eventLimit: true, // allow "more" link when too many events
      events: [
          {
              title: 'All Day Event',
              start: '2015-02-01'
          },
          {
              title: 'Long Event',
              start: '2015-02-07',
              end: '2015-02-10'
          },
          {
              id: 999,
              title: 'Repeating Event',
              start: '2015-02-09T16:00:00'
          },
          {
              id: 999,
              title: 'Repeating Event',
              start: '2015-02-16T16:00:00'
          },
          {
              title: 'Conference',
              start: '2015-02-11',
              end: '2015-02-13'
          },
          {
              title: 'Paciente Diogenes Dorete',
              start: '2015-02-12T10:00:00',
              end: '2015-02-12T10:15:00'
          },
          {
              title: 'Paciente Wagner Jeronimo',
              start: '2015-02-12T10:15:00',
              end: '2015-02-12T10:30:00'
          },
          {
              title: 'Paciente José Mauro',
              start: '2015-02-12T10:30:00',
              end: '2015-02-12T10:45:00'
          },
          {
              title: 'Paciente Japones',
              start: '2015-02-12T10:45:00',
              end: '2015-02-12T11:00:00'
          },
          {
              title: 'Lunch',
              start: '2015-02-12T12:00:00'
          },
          {
              title: 'Meeting',
              start: '2015-02-12T14:30:00'
          },
          {
              title: 'Happy Hour',
              start: '2015-02-12T17:30:00'
          },
          {
              title: 'Dinner',
              start: '2015-02-12T20:00:00'
          },
          {
              title: 'Birthday Party',
              start: '2015-02-13T07:00:00'
          },
          {
              title: 'Click for Google',
              url: 'http://google.com/',
              start: '2015-02-28'
          }
      ]
  });
