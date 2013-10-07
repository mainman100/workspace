from faculty.models import Course

def courses(request):
    courses = Course.objects.all()
    return {'courses':courses}
