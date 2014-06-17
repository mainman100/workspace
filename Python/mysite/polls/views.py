from django.core.urlresolvers import reverse
from django.http.response import HttpResponse, Http404, HttpResponseRedirect
from django.shortcuts import render, get_object_or_404
from polls.models import *

def index(request):
    latest_polls_list = Poll.objects.order_by('pub_date')[:5]
    return render(request, "polls/index.html", {"latest_polls_list":latest_polls_list})
def detail(request, poll_id):
    poll = get_object_or_404(Poll, id=poll_id)
    return render(request, 'polls/detail.html', {'poll': poll})
def vote(request, poll_id):
    p = get_object_or_404(Poll, id=poll_id)
    try:
        selected_choice = p.choice_set.get(id=request.POST['choice'])
    except (KeyError, choice.DoesNotExist):
        return render(request, 'polls/detail.html', {
                       'poll':p, 
                       'error_message':"You didn't select a choice."
        })
    else:
        selected_choice.votes+=1
        selected_choice.save()
        return HttpResponseRedirect(reverse('polls:results',args=(p.id,)))
def results(request, poll_id):
    poll = get_object_or_404(Poll, id=poll_id)
    return render(request, 'polls/results.html', {'poll': poll})    
