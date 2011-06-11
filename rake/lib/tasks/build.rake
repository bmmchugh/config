require 'java/task'
Java::BuildTask.new(ROOT_PATH)

task :default => :test
