import './styles/index.scss';

var $ = require('jquery');
var toastr = require('toastr');
var bootstrap = require('bootstrap');

window.jQuery = $;
window.$ = $;

window.toastr = toastr;
window.toastr.options =  {
   'closeButton': false,
   'debug': false,
   'newestOnTop': false,
   'progressBar': false,
   'positionClass': 'toast-top-right',
   'preventDuplicates': false,
   'onclick': null,
   'showDuration': '300',
   'hideDuration': '1000',
   'timeOut': '5000',
   'extendedTimeOut': '1000',
   'showEasing': 'swing',
   'hideEasing': 'linear',
   'showMethod': 'fadeIn',
    'hideMethod': 'fadeOut'
}