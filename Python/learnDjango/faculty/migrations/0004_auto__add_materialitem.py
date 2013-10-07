# -*- coding: utf-8 -*-
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models


class Migration(SchemaMigration):

    def forwards(self, orm):
        # Adding model 'MaterialItem'
        db.create_table('faculty_materialitem', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('course', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['faculty.Course'])),
            ('materialType', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['faculty.MaterialType'])),
            ('file', self.gf('django.db.models.fields.files.FileField')(max_length=100)),
        ))
        db.send_create_signal('faculty', ['MaterialItem'])


    def backwards(self, orm):
        # Deleting model 'MaterialItem'
        db.delete_table('faculty_materialitem')


    models = {
        'faculty.course': {
            'Meta': {'object_name': 'Course'},
            'description': ('django.db.models.fields.TextField', [], {}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'title': ('django.db.models.fields.CharField', [], {'max_length': '50'})
        },
        'faculty.materialitem': {
            'Meta': {'object_name': 'MaterialItem'},
            'course': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['faculty.Course']"}),
            'file': ('django.db.models.fields.files.FileField', [], {'max_length': '100'}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'materialType': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['faculty.MaterialType']"})
        },
        'faculty.materialtype': {
            'Meta': {'object_name': 'MaterialType'},
            'description': ('django.db.models.fields.TextField', [], {'null': 'True', 'blank': 'True'}),
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'title': ('django.db.models.fields.CharField', [], {'max_length': '50'})
        }
    }

    complete_apps = ['faculty']