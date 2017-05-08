var gulp = require('gulp');
var Nw = require('nw-builder');
var gutil = require('gulp-util');
var shell = require('shelljs');


gulp.task('default', function() {
    build(['win32']);

});


gulp.task('win32', function () {
    build(['win32']);
});


gulp.task('pack:win32', ['win32'], function() {
    return shell.exec('makensis ./installer-windows/installer.nsi');
});

function build(platform){
    var nw = new Nw({
        version : '0.13.1',
        files: [ './presentation/**'],
        platforms: platform, // change this to 'win' for/on windows,
        winIco : "logo.icon"
    });

    // Log stuff you want
    nw.on('log', function (msg) {
        gutil.log('node-webkit-builder', msg);
    });

    // Build returns a promise, return it so the task isn't called in parallel
    return nw.build().catch(function (err) {
        gutil.log('node-webkit-builder', err);
    });
}