from django.contrib import admin
from models import Course, MaterialType,MaterialItem


class MaterialItemAdmin(admin.ModelAdmin):
    list_display=['__unicode__','course','materialType']
    search_fields=['course__title']
    list_filter=['course']
admin.site.register(Course)
admin.site.register(MaterialType)
admin.site.register(MaterialItem,MaterialItemAdmin)
