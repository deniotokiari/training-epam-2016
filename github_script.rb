require 'net/http'
require 'openssl'
require 'json'

OpenSSL::SSL::VERIFY_PEER = OpenSSL::SSL::VERIFY_NONE

# read https://developer.github.com/guides/basics-of-authentication/
GIT_HUB_TOKEN = ''
GIT_HUB_BASE_URL = 'https://api.github.com/'
GIT_USER = 'deniotokiari'
GIT_REPO = 'training-epam-2016' 

PER_PAGE = '50'

def user_info(user)
	url = URI("#{GIT_HUB_BASE_URL}users/#{user}")
	req = Net::HTTP::Get.new(url)
	req['Authorization'] = "Basic #{GIT_HUB_TOKEN}"

	res = Net::HTTP.start(url.hostname, url.port, :use_ssl => url.scheme == 'https') { |http|
    	http.request(req)
    }

    json = JSON.parse(res.body)

    {:name=> json['name'], :email=> json['email'], :avatar=> json['avatar_url']}
end

def user_repo(user)
	url = URI("#{GIT_HUB_BASE_URL}users/#{user}/repos")
	req = Net::HTTP::Get.new(url)
	req['Authorization'] = "Basic #{GIT_HUB_TOKEN}"

	res = Net::HTTP.start(url.hostname, url.port, :use_ssl => url.scheme == 'https') { |http|
    	http.request(req)
    }

    json = JSON.parse(res.body)

    json.sort { |a, b| b['name'].downcase.scan(/(epam|android|2016|tr.+ning)/).size - a['name'].downcase.scan(/(epam|android|2016|tr.+ning)/).size }.first['name']
end

def repo_stats(user, repo)
	url = URI("#{GIT_HUB_BASE_URL}repos/#{user}/#{repo}/stats/contributors")
	req = Net::HTTP::Get.new(url)
	req['Authorization'] = "Basic #{GIT_HUB_TOKEN}"

	res = Net::HTTP.start(url.hostname, url.port, :use_ssl => url.scheme == 'https') { |http|
    	http.request(req)
    }

    json = JSON.parse(res.body)

    stats = json.select { |stat| stat['author']['login'] == user }.first

    if stats
    	weeks_data = stats['weeks']
    	additions = weeks_data.inject(0) { |sum, week| sum += week['a'] }
    	deletions = weeks_data.inject(0) { |sum, week| sum += week['d'] }

    	{:total=> stats['total'], :additions=> additions, :deletions=> deletions}
    else
    	{:total=> 0, :additions=> 0, :deletions=> 0}
    end
end

page = 1
issues = []

loop do
	url = URI("#{GIT_HUB_BASE_URL}repos/#{GIT_USER}/#{GIT_REPO}/issues?per_page=#{PER_PAGE}&page=#{page}&state=all")
	req = Net::HTTP::Get.new(url)
	req['Authorization'] = "Basic #{GIT_HUB_TOKEN}"

	res = Net::HTTP.start(url.hostname, url.port, :use_ssl => url.scheme == 'https') { |http|
    	http.request(req)
    }

    json = JSON.parse(res.body)

    issues << json

    page += 1

    break if json.empty?
end 

puts ''

issues
	.flatten
	.map { |issue| issue['user']['login'] }
	.uniq
	.select { |user| user != 'IstiN' && user != 'alexdzeshko' && user != 'deniotokiari' && user != 'ilya-shknaj' }
	.map { |user| 
		info = user_info(user)
		repo = user_repo(user)
		repo_data = repo_stats(user, repo)

		{:user=> user, :repo=> repo, :repo_data=> repo_data, :name=> info[:name]} 
	} 
	.sort { |a, b| b[:repo_data][:total] - a[:repo_data][:total] }
	.each_with_index { |item, i| puts "#{i + 1}: #{item[:user]} [#{item[:name]}] (#{item[:repo]} => #{item[:repo_data][:total]}, +#{item[:repo_data][:additions]}, -#{item[:repo_data][:deletions]})" }


	
