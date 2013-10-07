from django.shortcuts import render
from faculty.models import Course

def index(request):
    return render(request, 'index.html')

def index_course(request):
    courses = Course.objects.all()
    return render(request, "course/index.html", {'courses':courses})

def show_course(request, id):
    course = Course.objects.get(pk=id)
    render(request, "course/show.html", {'course':course})
