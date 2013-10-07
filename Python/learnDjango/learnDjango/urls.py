from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'learnDjango.views.home', name='home'),
    # url(r'^learnDjango/', include('learnDjango.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),
    url(r'^$', 'faculty.views.index'),
    url(r'^faculty/', include('faculty.urls')),
    url(r'^admin/', include(admin.site.urls)),
)
