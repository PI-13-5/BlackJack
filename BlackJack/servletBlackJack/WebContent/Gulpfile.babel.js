import gulp from 'gulp';
import babel from 'gulp-babel';
import sass from 'gulp-sass';
import clean from 'gulp-clean';
import uglify from 'gulp-uglify';
import concat from 'gulp-concat';
import jasmine from 'gulp-jasmine';
import autoprefixer from 'gulp-autoprefixer';
import eslint from 'gulp-eslint';
import morecss from 'gulp-more-css';
import shorthand from 'gulp-shorthand';
import uncss from 'gulp-uncss';


// Gulp task for compiling sass files.
// All the styles have to be imported to
// the main.scss file.
gulp.task('sass', () => {
    gulp
        .src('./sass/main.scss')
        .pipe(sass({
                outputStyle: 'compressed',
                errLogToConsole: true
            })
            .on('error', sass.logError))
        .pipe(autoprefixer())
        .pipe(shorthand())
        .pipe(morecss())
        .pipe(gulp.dest('./production/styles/'));
});


// Gulp task for watching and livereloading
// scss styles in sass folder.
gulp.task('sass:watch', () => {
    gulp
        .watch('./sass/**/*.scss', ['sass'])
        .on('change', (event) => {
            console.log(`File ${event.path} was ${event.type}, running tasks...`);
        });
});


// Gulp task for transpiling ES6/ES7 code, uglifies and
// minifies it, concatenates all files from ./js folder
// into ./production/js/all.min.js
gulp.task('js', () => {
    gulp
        .src('./js/**/*.js')
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError())
        .pipe(babel({
                plugins: ['transform-runtime'],
                presets: ['es2015']
            })
        )
        .pipe(uglify())
        .pipe(concat('all.min.js'))
        .pipe(gulp.dest('./production/js/'));
});


// Gulp task for watching and livereloading
// js styles in ./js folder.
gulp.task('js:watch', () => {
    gulp
        .watch('./js/**/*.js', ['js'])
        .on('change', (event) => {
            console.log(`File ${event.path} was ${event.type}, running tasks...`);
        });
});
