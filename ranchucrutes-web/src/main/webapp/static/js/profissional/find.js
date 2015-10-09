var map;


function initialize() {

  // Set up the map
  var mapOptions = {
    center: you,
    zoom: 10,
    streetViewControl: false
  };


  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);


  var markersInfos = new Map();
  for (var i = 0; i < profissionais.length; i++){
      // Setup the markers on the map

      //var infowindow = ;


     var profissionalMarker = new google.maps.Marker({position: profissionais[i].latlng,
                                                map: map,
                                                icon: 'http://www.hillel.org/img/Map-Icon-Hillel.png',
                                                title: profissionais[i].nome,
                                                id:i
                                            });

      markersInfos [profissionalMarker.id] = {marker:profissionalMarker,
                        info:new google.maps.InfoWindow({
                                        content: "<div id=\content\> " +
                                        "<img src='http://agendee.com.br/f/" + profissionais[i].crm + ".jpg' width:\"60px\" height=\"60px;\">" +
                                        " <br> <b>Nome:</b> " + profissionais[i].nome +
                                        " <br> <b>crm: </b>" + profissionais[i].crm +
                                        " <br> <b>Espec:</b> " + profissionais[i].espec +
                                        " <br> <b>Endereço:</b> " + profissionais[i].endereco +
                                        " <br> <b>Telefone:</b> " + profissionais[i].telefone +
                                        "</div>"
                                    })};


  }

  for (var key in markersInfos){
       //adicionando os markers e o info de cada um
       google.maps.event.addListener(markersInfos[key].marker, 'click', make_callback(key));
  }

  //criando a funcao de callback pq se criar  dentro da funcao de cima o key seria o ultimo do loop.
  function make_callback(key) {
      return function() {

          closeAllInfos();
          markersInfos[key].info.open(map,markersInfos[key].marker);
      };
  }

  function closeAllInfos(){
      for (var key in markersInfos){
             markersInfos[key].info.close();
        }
  }

  var bankMarker = new google.maps.Marker({
      position: you,
      map: map,
      icon: 'http://maps.gstatic.com/mapfiles/icon_green.png',
      title: 'Você está aqui'
  });

/*
  var busMarker = new google.maps.Marker({
      position: busStop,
      map: map,
      icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=bus|FFFF00',
      title: 'Bus Stop'
  });
*/
  // We get the map's default panorama and set up some defaults.
  // Note that we don't yet set it visible.
  panorama = map.getStreetView();
  panorama.setPosition(you);
  panorama.setPov(/** @type {google.maps.StreetViewPov} */({
    heading: 265,
    pitch: 0
  }));
}

google.maps.event.addDomListener(window, 'load', initialize);
