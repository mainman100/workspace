from django.db import models
import os, shutil
from django.dispatch.dispatcher import receiver
from django.db.models.signals import post_delete

class Course(models.Model):
    title = models.CharField(max_length=50)
    description = models.TextField()
    
    def __unicode__(self):
        return self.title

class MaterialType(models.Model):
    title = models.CharField(max_length=50)
    description = models.TextField(blank=True, null=True)
    
    def __unicode__(self):
        return self.title


def upload_course_files(instance, filename):
    return "{}/{}/{}".format(instance.course.title, instance.materialType.title, filename)
    
class MaterialItem(models.Model):
    course = models.ForeignKey(Course)
    materialType = models.ForeignKey(MaterialType)
    file = models.FileField(upload_to=upload_course_files)
    class Meta:
        # to ensure that in the admin area, each course content is dispalyed sequentially 
        ordering = ["course__title", "materialType"]
    def __unicode__(self):
        return os.path.basename(self.file.name)
    
@receiver(post_delete, sender=MaterialItem, dispatch_uid="materialItem_post_delete")
def delete_associated_file(sender, **kwargs):
    item = kwargs['instance']
    os.remove(item.file.path)
