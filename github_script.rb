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

def fetch(uri)
	url = URI(uri)
	req = Net::HTTP::Get.new(url)
	req['Authorization'] = "Basic #{GIT_HUB_TOKEN}"

	res = Net::HTTP.start(url.hostname, url.port, :use_ssl => url.scheme == 'https') { |http| http.request(req) }

	case res
	when Net::HTTPSuccess then res.body
	when Net::HTTPRedirection then fetch(res['location'])
	else 
		""
	end
end

def user_info(user)
    json = JSON.parse(fetch("#{GIT_HUB_BASE_URL}users/#{user}"))

    {:name=> json['name'], :email=> json['email'], :avatar=> json['avatar_url']}
end

def user_repo(user)
    json = JSON.parse(fetch("#{GIT_HUB_BASE_URL}users/#{user}/repos"))

    json.sort { |a, b| b['name'].downcase.scan(/(epam|android|2016|tr.+ning)/).size - a['name'].downcase.scan(/(epam|android|2016|tr.+ning)/).size }.first['name']
end

def repo_stats(user, repo)
    json = JSON.parse(fetch("#{GIT_HUB_BASE_URL}repos/#{user}/#{repo}/stats/contributors"))

    if json.kind_of?(Array)
	    stats = json.size == 1 ? json.first : json.select { |stat| stat['author']['login'] == user }.first

	    if stats
	    	weeks_data = stats['weeks']
	    	additions = weeks_data.inject(0) { |sum, week| sum += week['a'] }
	    	deletions = weeks_data.inject(0) { |sum, week| sum += week['d'] }

	    	{:total=> stats['total'], :additions=> additions, :deletions=> deletions}
	    else
	    	{:total=> 0, :additions=> 0, :deletions=> 0}
	    end
	else
		{:total=> 0, :additions=> 0, :deletions=> 0}
	end
end

page = 1
issues = []

loop do
    json = JSON.parse(fetch("#{GIT_HUB_BASE_URL}repos/#{GIT_USER}/#{GIT_REPO}/issues?per_page=#{PER_PAGE}&page=#{page}&state=all"))

    issues << json

    page += 1

    break if json.empty?
end 

puts ''

data = {}

issues
	.flatten
	.select { |user| user != 'IstiN' && user != 'alexdzeshko' && user != 'deniotokiari' && user != 'ilya-shknaj' }
	.map { |issue| {:login=> issue['user']['login'], :body=> issue['body']} }
	.select { |issue| !issue[:body].match(/https:\/\/github.com.+/).nil? }
	.map { |issue| 
		repo = issue[:body].scan(/https:\/\/github.com.+/)
		issue.delete(:body)

		issue[:repo] = repo
		.map { |rep| 
			if rep.split(/\//).size == 5 
				rep.strip.split(' ').first
			else 
				rep.match(/https:\/\/github.com\/.+\/.+\//)[0].split(/\//)[0..4].join('/').split(' ').first
			end
		}
		.map { |rep| rep.gsub('.git', '') }
		.uniq
		.first
		.split('/')
		.last

		issue
	}
	.each { |info|
		if data.include?(info[:login])
			data[info[:login]] << info[:repo]
		else
			data[info[:login]] = [info[:repo]]
		end
	}

data
	.map { |key, value| {:login=> key, :repo=> value.uniq} }
	.map { |item|
		info = user_info(item[:login])
		repo_data = item[:repo].map { |rep| repo_stats(item[:login], rep) }

		commits = repo_data.inject(0) { |sum, stat| sum += stat[:total] }
		additions = repo_data.inject(0) { |sum, stat| sum += stat[:additions] }
		deletions = repo_data.inject(0) { |sum, stat| sum += stat[:deletions] }

		{:user=> item[:login], :repo_data=> {:total=> commits, :additions=> additions, :deletions=> deletions}, :name=> info[:name]} 
	}
	.sort { |a, b| b[:repo_data][:total] - a[:repo_data][:total] }
	.each_with_index { |item, i| puts "#{i + 1}: #{item[:user]} [#{item[:name]}] => #{item[:repo_data][:total]}, +#{item[:repo_data][:additions]}, -#{item[:repo_data][:deletions]}" }