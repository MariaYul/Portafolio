<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="es-MX">
<head>
  <meta charset="ISO-8859-1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1"/> -->
  <meta name="viewport" content="width = 1050, user-scalable = no" />
  <meta name="description" content="Sistema de Capacitación Técnica para gestionar los documentos de los candidatos, empleados y ex-empleados de Telcel" />
  <meta name="author" content="Emmanuel Delgado Mej&iacute;a" />
  
  <title>Expediente FlippingBook</title>
  
  <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen"/>
  <link href="<c:url value="/css/main.css"/>" rel="stylesheet"/>
    
  <script src="<c:url value="/js/jquery.js"/>"/></script>
  <script src="<c:url value="/js/modernizr.2.5.3.min.js"/>"/></script>
  <script src="<c:url value="/js/hash.js"/>"/></script>
  
  <link rel="icon" type="image/png" href="<c:url value="/images/foldericon.png"/>"/>
</head>
<body>
    <div id="canvas">
        <div class="zoom-icon zoom-icon-in"></div>

        <div class="magazine-viewport">
            <div class="container">
                <div class="magazine">
                    <!-- Botón siguiente -->
                    <div ignore="1" class="next-button"></div>
                    <!-- Botón Previo -->
                    <div ignore="1" class="previous-button"></div>
                </div>
            </div>
        </div>

        <!-- Thumbnails -->
        <div class="thumbnails">
            <div>
                <ul>
                    <c:if test="${not empty imagenes}">
                        <c:forEach items="${imagenes}" var="imagen" varStatus="contador">
                            <c:choose>
                                <c:when test="${contador.count == 1}">
                                    <li class="i">
                                        <img src="<c:url value="${imagen}"/>" width="76" height="100" class="page-${contador.count}"/>
                                    </li>
                                </c:when>
                                <c:when test="${contador.count == fn:length(imagenes)}">
                                    <li class="i">
                                        <img src="<c:url value="${imagen}"/>" width="76" height="100" class="page-${contador.count}"/>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${contador.count%2 == 0}">
                                            <li class="d">
                                                <img src="<c:url value="${imagen}"/>" width="76" height="100" class="page-${contador.count}"/>
                                        </c:when>
                                        <c:otherwise>
                                                <img src="<c:url value="${imagen}"/>" width="76" height="100" class="page-${contador.count}"/>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:if>
                  </ul>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">

    //Inicio de loadApp
    function loadApp() {
         $('#canvas').fadeIn(1000);
         var flipbook = $('.magazine');

        //Checa si el CSS ya está cargado
        if (flipbook.width()==0 || flipbook.height()==0) {
            setTimeout(loadApp, 10);
            return;
        }

      //Obtiene la cantidad de imágenes cargadas en el flipbook
        var magazinePagesNumber = $('.thumbnails div ul li img').length;

        //Crea el flipbook
        flipbook.turn({
                width: 922
                , height: 600
                , duration: 1000
                //, acceleration: !isChrome()
                , gradients: true
                , autoCenter: true
                , elevation: 50
                //El parámetro pages es muy necesario para determinar el número de páginas a mostrar
                , pages: magazinePagesNumber,

                //Eventos
                when: {
                    missing: function (event, pages) {
                        //Agrega páginas que no están dentro de la revista
                        for (var i = 0; i < pages.length; i++)
                            addPage(pages[i], $(this));
                    },

                    turning: function(event, page, view) {
                        var book = $(this),
                        currentPage = book.turn('page'),
                        pages = book.turn('pages');

                        //Actualiza la URI actual (en el navegador)
                        Hash.go('page/' + page).update();

                        //Muestra y oculta los botones de navegación
                        disableControls(page);

                        $('.thumbnails .page-'+currentPage).
                            parent().
                            removeClass('current');

                        $('.thumbnails .page-'+page).
                            parent().
                            addClass('current');
                    },

                    turned: function(event, page, view) {
                        disableControls(page);
                        $(this).turn('center');

                        if (page==1) {
                            $(this).turn('peel', 'br');
                        }
                    }
                }
        });

        // Zoom.js
        $('.magazine-viewport').zoom({
            flipbook: $('.magazine')
            , max: function() {
                return largeMagazineWidth()/$('.magazine').width();
            },
            when: {
                swipeLeft: function() {
                    $(this).zoom('flipbook').turn('next');
                },

                swipeRight: function() {
                    $(this).zoom('flipbook').turn('previous');
                },

                resize: function(event, scale, page, pageElement) {
                    if (scale==1)
                        loadSmallPage(page, pageElement);
                    else
                        loadLargePage(page, pageElement);
                },

                zoomIn: function () {
                    $('.thumbnails').hide();
                    $('.made').hide();
                    $('.magazine').removeClass('animated').addClass('zoom-in');
                    $('.zoom-icon').removeClass('zoom-icon-in').addClass('zoom-icon-out');

                    if (!window.escTip && !$.isTouch) {
                        escTip = true;

                        $('<div />', {'class': 'exit-message'}).
                            html('<div>Presione ESC para salir</div>').
                                appendTo($('body')).
                                delay(2000).
                                animate({opacity:0}, 500, function() {
                                    $(this).remove();
                                });
                    }
                },

                zoomOut: function () {
                    $('.exit-message').hide();
                    $('.thumbnails').fadeIn();
                    $('.made').fadeIn();
                    $('.zoom-icon').removeClass('zoom-icon-out').addClass('zoom-icon-in');

                    setTimeout(function(){
                        $('.magazine').addClass('animated').removeClass('zoom-in');
                        resizeViewport();
                    }, 0);
                }
            }
        });

        //Evento Zoom
        if ($.isTouch)
            $('.magazine-viewport').bind('zoom.doubleTap', zoomTo);
        else
            $('.magazine-viewport').bind('zoom.tap', zoomTo);


        //Uso de las flechas del teclado para cambiar de página
        $(document).keydown(function(e){
            //var previous = 37, next = 39, esc = 27;
            var previous = 37, last = 38, next = 39, first = 40, esc = 27;

            switch (e.keyCode) {
                case previous:
                    //Flecha izquierda
                    $('.magazine').turn('previous');
                    e.preventDefault();
                break;
                
                case last:
                    //Flecha arriba
                    $('.magazine').turn('page', magazinePagesNumber);
                    e.preventDefault();
                break;

                case next:
                    //Flecha derecha
                    $('.magazine').turn('next');
                    e.preventDefault();
                break;
                
                case first:
                    //Flecha abajo
                    $('.magazine').turn('page', 1);
                    e.preventDefault();
                break;

                case esc:
                    $('.magazine-viewport').zoom('zoomOut');
                    e.preventDefault();
                break;
            }
        });

        //URIs - Formato #/page/1
        Hash.on('^page\/([0-9]*)$', {
            yep: function(path, parts) {
                var page = parts[1];

                if (page!==undefined) {
                    if ($('.magazine').turn('is'))
                        $('.magazine').turn('page', page);
                }

            },
            nop: function(path) {
                if ($('.magazine').turn('is'))
                    $('.magazine').turn('page', 1);
            }
        });


        $(window).resize(function() {
            resizeViewport();
        }).bind('orientationchange', function() {
            resizeViewport();
        });

        //Eventos para miniaturas
        $('.thumbnails').click(function(event) {
            var page;

            if (event.target && (page=/page-([0-9]+)/.exec($(event.target).attr('class'))) ) {
                $('.magazine').turn('page', page[1]);
            }
        });

        $('.thumbnails li').
            bind($.mouseEvents.over, function() {
                $(this).addClass('thumb-hover');
            }).bind($.mouseEvents.out, function() {
                $(this).removeClass('thumb-hover');
            });

        if ($.isTouch) {
            $('.thumbnails').
                addClass('thumbanils-touch').
                bind($.mouseEvents.move, function(event) {
                    event.preventDefault();
                });
        } else {
            $('.thumbnails ul').mouseover(function() {
                $('.thumbnails').addClass('thumbnails-hover');
            }).mousedown(function() {
                return false;
            }).mouseout(function() {
                $('.thumbnails').removeClass('thumbnails-hover');
            });
        }

        /*//Regiones
        if ($.isTouch) {
            $('.magazine').bind('touchstart', regionClick);
        } else {
            $('.magazine').click(regionClick);
        }*/

        //Eventos para el botón siguiente
        $('.next-button').bind($.mouseEvents.over, function() {
            $(this).addClass('next-button-hover');
        }).bind($.mouseEvents.out, function() {
            $(this).removeClass('next-button-hover');
        }).bind($.mouseEvents.down, function() {
            $(this).addClass('next-button-down');
        }).bind($.mouseEvents.up, function() {
            $(this).removeClass('next-button-down');
        }).click(function() {
            $('.magazine').turn('next');
        });

        //Eventos para el botón previo
        $('.previous-button').bind($.mouseEvents.over, function() {
            $(this).addClass('previous-button-hover');
        }).bind($.mouseEvents.out, function() {
            $(this).removeClass('previous-button-hover');
        }).bind($.mouseEvents.down, function() {
            $(this).addClass('previous-button-down');
        }).bind($.mouseEvents.up, function() {
            $(this).removeClass('previous-button-down');
        }).click(function() {
            $('.magazine').turn('previous');
        });

        resizeViewport();

        $('.magazine').addClass('animated');
    }
    //Fin de loadApp

    //Icono de zoom
    $('.zoom-icon').bind('mouseover', function() {
         if ($(this).hasClass('zoom-icon-in'))
             $(this).addClass('zoom-icon-in-hover');

         if ($(this).hasClass('zoom-icon-out'))
             $(this).addClass('zoom-icon-out-hover');

     }).bind('mouseout', function() {
          if ($(this).hasClass('zoom-icon-in'))
             $(this).removeClass('zoom-icon-in-hover');

         if ($(this).hasClass('zoom-icon-out'))
             $(this).removeClass('zoom-icon-out-hover');

     }).bind('click', function() {
         if ($(this).hasClass('zoom-icon-in'))
             $('.magazine-viewport').zoom('zoomIn');
         else if ($(this).hasClass('zoom-icon-out'))
            $('.magazine-viewport').zoom('zoomOut');

     });

     $('#canvas').hide();


    //Carga la versión HTML4 si no está la transformación CSS
    yepnope({
        test : Modernizr.csstransforms,
        yep: ['./js/turn.js'],
        nope: ['./js/turn.html4.min.js'],
        both: ['./js/zoom.min.js', './js/magazine.js', './css/magazine.css'],
        complete: loadApp
    });
    </script>
</body>
</html>