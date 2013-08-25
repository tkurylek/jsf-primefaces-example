/*jslint browser: true, plusplus: true, vars: true, white: true */
/*global jQuery, $, google*/
var GmapGeocoding = function(gmap, gmapDialog) {
    "use strict";
    var map = gmap.getMap();
    var geocodingApiUrl = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false";

    // appends geocodingApiUrl with given query
    function createGeocodingApiUrlWithQuery(param, value) {
        return geocodingApiUrl + "&" + param + "=" + value;
    }

    // centers map to given address using geocoding service by Google
    // this method will be applied to gmap widget of primefaces
    gmap.moveToAddress = function(address) {
        var lat, lng, url = createGeocodingApiUrlWithQuery('address', address);
        $.get(url, function(data) {
            if (data.status === 'OK' && data.results.length > 0) {
                lat = data.results[0].geometry.location.lat;
                lng = data.results[0].geometry.location.lng;
                map.setCenter(new google.maps.LatLng(lat, lng));
                map.setZoom(17);
                gmapDialog.show();
            } else {
                $.error(data);
            }
        });
    };
}