var CONST_SITE = "SIGM";
var CONST_CURRENT_VER = "3.0.1";
var CONST_SITE_TARGET_DIR = "/_site/";

function isDocumentationSiteViewedLocally() {
    return location.href.indexOf(CONST_SITE_TARGET_DIR) != -1;
}

function getActiveDocumentationVersionInView(returnBlankIfNoVersion) {
    var href = location.href;
    var uri = new URI(document.location);
    // Saber si estamos viendo la versión en local o en GitHub
    var refLocal=( CONST_SITE == uri.segment(0) ) ? 3 : 2 ;

    var currentVersion = CONST_CURRENT_VER;
    var index = isDocumentationSiteViewedLocally() ? href.indexOf(CONST_SITE_TARGET_DIR) : -1;

    if (index == -1) {
        if ( "index.html" == uri.filename()) {
            return href.substring( href.indexOf(uri.segment(0)), href.lastIndexOf("/"));
        } else {
            return href.substring( href.indexOf(uri.segment(0)), href.indexOf(uri.segment(refLocal)) -1 );
/*
            if (uri.filename() != uri.segment(1) && uri.segment(1) != CONST_CURRENT_VER ) {
                currentVersion = uri.segment(1);
            } else if (returnBlankIfNoVersion) {
                return "";
            }
*/
        }
    } else {
        href = href.substring(index + 7);
        index = href.indexOf("/");
        currentVersion = href.substring(0, index);
    }
    return currentVersion;
}


function loadSidebarForActiveVersion() {
    $.get( "/" + getActiveDocumentationVersionInView() + "/sidebar.html", function( data ) {

        var menu = $(data);

        if ( menu.first().is('ul') ) {

            // menu.addClass('nav collapse').attr('id', 'sidebarTopics');
            menu.addClass('nav').attr('id', 'sidebarTopics');

            var topLevel = menu.find('> li>a');

            var subLevel = menu.find('> li ul');

            var nestedMenu = menu.find("ul li" ).has( "ul" ).children('a');

            topLevel.each(function() {
                sidebarTopNav( $(this) );
            });

            subLevel.each( function() {
                sidebarSubNav( $(this) );
            });

            nestedMenu.each(function() {
                sidebarTopNav( $(this) );
            });

            $('#sidebartoc').append(menu);

            generateSidebarLinksForActiveVersion();

        } else {
            $('#sidebartoc').hide();
        }


    });
}

function sidebarTopNav( el ) {
    // If the link is an anchor, then wire up toggle functionality, otherwise leave it.
    if ( el.attr('href').search(/(?:^|)#/g) >= 0 ) {
        el.attr({
            'data-toggle': "collapse",
            'data-parent': "#" + $(this).closest('ul').attr('id'),
            'aria-expanded': "false",
            title: $(this)[0].innerText,
            class: 'collapsed'
        })
        .append('<i class="expand"></i></a>');
    } else {

    }
};


function sidebarSubNav( el ) {
    var prevId = $(el).prev('a').attr('href');

    if ( prevId.search(/^#.*$/) >= 0) {
        prevId = prevId.substr(1);
    } else {
        prevId = '';
    }

    if (!prevId == '') {
        $(el).addClass('nav collapse').attr('id', prevId);
    }
}

function hideDevelopmentVersionWarning() {
    var formattedVersion = getActiveDocumentationVersionInView(true);
    if (formattedVersion != CONST_CURRENT_VER || formattedVersion == "") {
        $("#dev-doc-info").hide();
    }
}

function getActiveVersionInView() {
    var uri = new URI(document.location);

    var retVal="";
    // La URL que estamos viendo... ¿empieza por SIGM?
    if ( CONST_SITE == uri.segment(0) ) {
       // Sip, estamos en GitHub
       retVal= uri.segment(0) + "/" + uri.segment(1); 
    } else {
       // No, estamos en Local
       retVal= uri.segment(0) ; 
    }
    return retVal;
}

function generateSidebarLinksForActiveVersion() {
    $('#sidebar a').each(function () {
        var href = this.href;

        if (href.indexOf("$version") != -1) {
            href = href.replace("$version", "" + getActiveVersionInView());
            $(this).attr('href', href);
        }
    });
}

function generateToolbarIcons() {
    return ;
}

function generateTableOfContentsForPage() {
    var toc = $('#tableOfContents ul');
    var page_contents = $('#pageContents ul');
    var arr = [];

    var headings = $('#sigm-docs-container').find('h1, h2,h3');
    var subMenu = false;
    var subMenuId = null;

    headings.each(function (idx) {
        if ($(this).is('h1,h2')) {
            // If it is a H2 and the submenu flag is NOT set, then arr.push('<li>h2 text')
            if (!subMenu) {
                arr.push( tocItem(this.id, this.textContent));
            }

            // If it is a H2 and the submenu flag is set, then arr.push('</ul><li>h2 text')
            if (subMenu) {
                subMenu = false;
                arr.push('</ul></li>');
                arr.push( tocItem(this.id, this.textContent));
            }
        }
        ; // End H2

        if ($(this).is('h3')) {
            // If it is a H3 and the submenu flag is NOT set, then set the submenu flag then arr.push('<ul><li>h3 text</li>')
            if (!subMenu) {
                subMenu = true;
                arr.push('<ul class="nav">');
                arr.push( tocItem(this.id, this.textContent));
            } else if (subMenu) {
                arr.push( tocItem(this.id, this.textContent));
            }
        }
        ; // End H2
    });

    //After the loop, close the last <li> tag
    if (subMenu) {
        arr.push('</ul></li>');
    } else {
        arr.push('</li>');
    }

    toc.append(arr.join(''));
    page_contents.append(arr.join(''));
}

function tocItem(id, text) {
    return '<li><a href="#' + id + '">' + text + '</a>';
}


function guidGenerator() {
    var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    };
    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}

function ensureBootrapIsLoaded() {
    if (typeof($.fn.modal) === 'undefined') {
        // require a minimum version of bootstrap
        $('head').prepend("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css'>");
        $('head').append("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js'></script>");
    }
}

function responsiveImages() {
    $('img').each(function() {
        $(this).addClass('img-fluid');
    });
}

function responsiveTables() {
    $('table').each(function() {
        $(this).addClass('table table-responsive');
    });
}

function copyButton() {
    $('div.highlight').each(function() {
        var btn = '<button class="copy-button hidden-md-down fa fa-clipboard" />';
        $(this).append( btn );
    });
}
var clipboard = new Clipboard('.copy-button', {
    target: function(trigger) {
        var code = $(trigger).prev('table').find('td.code pre')[0];
        return code;
    }
});
clipboard.on('success', function(e) {
    e.clearSelection();
});

$(function () {
    ensureBootrapIsLoaded();
    loadSidebarForActiveVersion();
    generateTableOfContentsForPage();

    generateToolbarIcons();
    responsiveImages();
    responsiveTables();

    copyButton();

    var formattedVersion = getActiveDocumentationVersionInView();
    if (formattedVersion != "" && formattedVersion.indexOf(CONST_CURRENT_VER) == -1) {
        formattedVersion = " (" + formattedVersion + ")"
    } else {
        formattedVersion = "";
    }
    hideDevelopmentVersionWarning();
    //document.title = $("h1").first().text() + formattedVersion;

    $('[data-toggle=offcanvas]').click(function () {
        $(this).toggleClass('visible-xs-up text-center');
        $(this).find('i').toggleClass('fa-chevron-left fa-chevron-right');
        $('.row-offcanvas').toggleClass('active');
        $('#lg-menu').toggleClass('hidden-xs-down');

    });

});


$(function() {
  return $("h2, h3, h4, h5, h6").each(function(i, el) {
    var $el, icon, id;
    $el = $(el);
    id = $el.attr('id');
    icon = '<i class="fa fa-link"></i>';
    if (id) {
      return $el.prepend($("<a />").addClass("header-link").attr("href", "#" + id).html(icon));
    }
  });
});

$(function() {
  if ( -1 != location.href.indexOf("3.0.1") ) {
    $("blockquote").first().toggleClass('aviso');
  }
});
