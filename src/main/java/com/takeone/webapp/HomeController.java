package com.takeone.webapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private Long currentId = 1L;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView mav) {
		// 사람들
		currentId = 1L;
		List<Person> males = new ArrayList<Person>();
		
		males.add(new Person(incrementAndGet(), "철수", Gender.MALE));
		males.add(new Person(incrementAndGet(), "경민", Gender.MALE));
		males.add(new Person(incrementAndGet(), "민수", Gender.MALE));
		males.add(new Person(incrementAndGet(), "우성", Gender.MALE));
		
		List<Person> females = new ArrayList<Person>();
		
		females.add(new Person(incrementAndGet(), "영희", Gender.FEMALE));
		females.add(new Person(incrementAndGet(), "태희", Gender.FEMALE));
		females.add(new Person(incrementAndGet(), "혜수", Gender.FEMALE));
		females.add(new Person(incrementAndGet(), "근영", Gender.FEMALE));
		
		// 선호도 
		males.get(0).setPreferences(getPreferences(females, new Integer[]{10, 9, 8, 7}));
		males.get(1).setPreferences(getPreferences(females, new Integer[]{8, 9, 10, 7}));
		males.get(2).setPreferences(getPreferences(females, new Integer[]{7, 9, 8, 10}));
		males.get(3).setPreferences(getPreferences(females, new Integer[]{10, 9, 8, 7}));
		
		females.get(0).setPreferences(getPreferences(males, new Integer[]{7, 9, 8, 10}));
		females.get(1).setPreferences(getPreferences(males, new Integer[]{8, 9, 7, 10}));
		females.get(2).setPreferences(getPreferences(males, new Integer[]{10, 9, 8, 7}));	
		females.get(3).setPreferences(getPreferences(males, new Integer[]{10, 9, 8, 7}));
		
		// 매칭 정보
		Map<Person, Person> matches = getMatches(females, males);		
		
		mav.addObject("matches", matches);
		System.out.println("hi");
		mav.setViewName("home");
		return mav;
	}
	
	/**
	 * 실제 Gale/Shapley 알고리즘 구현
	 * @param females 여성
	 * @param males 남성 
	 * @return
	 */
	private Map<Person, Person> getMatches(List<Person> females, List<Person> males) {
		Map<Person, Person> engagedTo = new TreeMap<Person, Person>(); 
		List<Person> freeGirls = new LinkedList<Person>();
		
		freeGirls.addAll(females);
		
		while(!freeGirls.isEmpty()) {
			Person thisGirl = freeGirls.remove(0);
			Queue<Preference> thisPreferences = thisGirl.getPreferences(); 
			
			for(Preference thisPreference : thisPreferences) {
				Person thisGuy = thisPreference.getPeoson();
				
				// 찾아온 사람이 없다면 연결
				if(engagedTo.get(thisGuy) == null) {
					thisGirl.setPreference(thisPreference);
					engagedTo.put(thisGuy, thisGirl);
					break;
				} else {
					Preference otherPreference = engagedTo.get(thisGuy).getPreference();

					// 기존에 왔던 사람보다 더 선호하는 사람이 오면 연결
					if(otherPreference.getScore() < thisPreference.getScore()) {
						thisGirl.setPreference(thisPreference);
						engagedTo.put(thisGuy, thisGirl);
						
						// 기존에 연결되어 있던 사람은 다시 시도 하기위해 추가
						freeGirls.add(otherPreference.getPeoson());
						break;
					}
					
				}
			}
		}

		return engagedTo;
	}
	
	
	/**
	 * 점수별 정렬
	 * @param peoples
	 * @param scores
	 * @return
	 */
	private Queue<Preference> getPreferences(List<Person> peoples, Integer[] scores) {
		Queue<Preference> preferences = new PriorityQueue<Preference>(10, new Comparator<Preference>() {
			public int compare(Preference p1, Preference p2) {
				if(p1.getScore() <= p2.getScore()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		for(int i = 0; i < scores.length; i++) {
			preferences.add(new Preference(peoples.get(i), scores[i]));
		}		
		
		return preferences;
	}
	
	
	private Long incrementAndGet() {
		return this.currentId++;
	}
}
