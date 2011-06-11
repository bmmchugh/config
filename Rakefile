ROOT_PATH = File.dirname(__FILE__).freeze
FileList[File.join(ROOT_PATH, 'rake', 'vendor', '*', 'lib')
].exclude(/rspec/, /test/).each do |vendor_lib|
  $LOAD_PATH << vendor_lib
  FileList[File.join(vendor_lib, 'tasks', '*.rake')].each do |f|
    Rake.application.add_import f
  end
end

rake_lib = File.join(ROOT_PATH, 'rake', 'lib')
$LOAD_PATH << rake_lib
FileList[File.join(rake_lib, 'tasks', '*.rake')].each do |f|
  Rake.application.add_import f
end
